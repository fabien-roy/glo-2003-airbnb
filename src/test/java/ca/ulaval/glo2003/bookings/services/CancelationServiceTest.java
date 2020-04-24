package ca.ulaval.glo2003.bookings.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.services.CancelationService.MINIMUM_DAYS_FOR_FULL_REFUND;
import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.transactions.domain.helpers.PriceObjectMother.createPrice;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.bookings.converters.CancelationConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.CancelationRefundCalculator;
import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;
import ca.ulaval.glo2003.bookings.exceptions.CancelationNotAllowedException;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.reports.services.ReportService;
import ca.ulaval.glo2003.time2.domain.TimeDate;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CancelationServiceTest {

  private static CancelationService cancelationService;
  private static CancelationRefundCalculator cancelationRefundCalculator =
      mock(CancelationRefundCalculator.class);
  private static CancelationConverter cancelationConverter = mock(CancelationConverter.class);
  private static TransactionService transactionService = mock(TransactionService.class);
  private static ReportService reportService = mock(ReportService.class);

  private static Bed bed = aBed().build();
  private static Booking booking;
  private static TimeDate arrivalDate;
  private static Price total;
  private static Price tenantRefund = createPrice();
  private static Price ownerRefund = createPrice();
  private CancelationResponse cancelationResponse = mock(CancelationResponse.class);

  @BeforeAll
  public static void setUpService() {
    cancelationService =
        new CancelationService(
            cancelationRefundCalculator, cancelationConverter, transactionService, reportService);
  }

  @BeforeEach
  public void resetBooking() {
    arrivalDate = aTimeDate().build();
    total = createPrice();
    booking = buildBooking();
    resetMocks();
  }

  private void resetMocks() {
    reset(cancelationConverter, cancelationRefundCalculator, reportService);
    when(cancelationConverter.toResponse(tenantRefund)).thenReturn(cancelationResponse);
    when(cancelationConverter.toResponse(total)).thenReturn(cancelationResponse);
    when(cancelationRefundCalculator.calculateTenantRefund(total)).thenReturn(tenantRefund);
    when(cancelationRefundCalculator.calculateOwnerRefund(total)).thenReturn(ownerRefund);
  }

  private static Booking buildBooking() {
    return aBooking().withArrivalDate(arrivalDate).withPrice(total).build();
  }

  private static void setBookingOnSameDay() {
    arrivalDate = TimeDate.now();
    booking = buildBooking();
  }

  private static void setBookingOnSameWeek() {
    arrivalDate = TimeDate.now().plusDays(1);
    booking = buildBooking();
  }

  private static void setOlderBooking() {
    arrivalDate = TimeDate.now().plusDays(MINIMUM_DAYS_FOR_FULL_REFUND);
    booking = buildBooking();
  }

  @Test
  public void cancel_withCanceledBooking_shouldThrowBookingAlreadyCanceledException() {
    booking.cancel();

    assertThrows(
        BookingAlreadyCanceledException.class, () -> cancelationService.cancel(bed, booking));
  }

  @Test
  public void cancel_withBookingOnSameDay_shouldThrowCancelationNotAllowedException() {
    setBookingOnSameDay();

    assertThrows(
        CancelationNotAllowedException.class, () -> cancelationService.cancel(bed, booking));
  }

  @Test
  public void cancel_withBookingOnSameWeek_shouldAddStayCanceledTransactions() {
    setBookingOnSameWeek();

    cancelationService.cancel(bed, booking);

    verify(transactionService)
        .addStayCanceledHalfRefund(
            eq(booking.getTenantPublicKey().toString()),
            eq(bed.getOwnerPublicKey().toString()),
            eq(tenantRefund),
            eq(ownerRefund),
            eq(total),
            eq(booking.getDepartureDate().toTimestamp()));
  }

  @Test
  public void cancel_withOlderBooking_shouldAddStayCanceledTransactions() {
    setOlderBooking();

    cancelationService.cancel(bed, booking);

    verify(transactionService)
        .addStayCanceledFullRefund(
            eq(booking.getTenantPublicKey().toString()),
            eq(bed.getOwnerPublicKey().toString()),
            eq(total),
            eq(booking.getDepartureDate().toTimestamp()));
  }

  @Test
  public void cancel_shouldCancelBooking() {
    booking = mock(Booking.class);
    when(booking.getTenantPublicKey()).thenReturn(createPublicKey());
    when(booking.getArrivalDate()).thenReturn(arrivalDate);
    when(booking.getDepartureDate()).thenReturn(arrivalDate);
    when(booking.getPrice()).thenReturn(total);

    cancelationService.cancel(bed, booking);

    verify(booking).cancel();
  }

  @Test
  public void add_shouldReportCancelation() {
    cancelationService.cancel(bed, booking);

    verify(reportService).addCancelation(bed, booking);
  }

  @Test
  public void cancel_withBookingOnSameWeek_shouldMapCancelationToResponse() {
    setBookingOnSameWeek();

    CancelationResponse actualCancelationResponse = cancelationService.cancel(bed, booking);

    assertSame(cancelationResponse, actualCancelationResponse);
  }

  @Test
  public void cancel_withOlderBooking_shouldMapCancelationToResponse() {
    setOlderBooking();

    CancelationResponse actualCancelationResponse = cancelationService.cancel(bed, booking);

    assertSame(cancelationResponse, actualCancelationResponse);
  }
}
