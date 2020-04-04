package ca.ulaval.glo2003.bookings.services;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.*;
import static ca.ulaval.glo2003.bookings.rest.helpers.CancelationResponseBuilder.aCancelationResponse;
import static ca.ulaval.glo2003.bookings.services.CancelationService.MINIMUM_DAYS_FOR_FULL_REFUND;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.bookings.converters.CancelationConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.CancelationRefundCalculator;
import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;
import ca.ulaval.glo2003.bookings.exceptions.CancelationNotAllowedException;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
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

  private static Booking booking;
  private static BookingDate arrivalDate;
  private static Price total;
  private static Price tenantRefund = createTotal();
  private static Price ownerRefund = createTotal();
  private static String bedOwner = "bedOwner";
  private CancelationResponse cancelationResponse = aCancelationResponse().build();

  @BeforeAll
  public static void setUpService() {
    cancelationService =
        new CancelationService(
            cancelationRefundCalculator, cancelationConverter, transactionService);
  }

  @BeforeEach
  public void resetBooking() {
    arrivalDate = createArrivalDate();
    total = createTotal();
    booking = buildBooking();
    resetMocks();
  }

  private void resetMocks() {
    reset(cancelationConverter, cancelationRefundCalculator);
    when(cancelationConverter.toResponse(tenantRefund)).thenReturn(cancelationResponse);
    when(cancelationConverter.toResponse(total)).thenReturn(cancelationResponse);
    when(cancelationRefundCalculator.calculateTenantRefund(total))
        .thenReturn(tenantRefund.getValue());
    when(cancelationRefundCalculator.calculateOwnerRefund(total))
        .thenReturn(ownerRefund.getValue());
  }

  private static Booking buildBooking() {
    return aBooking().withArrivalDate(arrivalDate).withTotal(total).build();
  }

  private static void setBookingOnSameDay() {
    arrivalDate = BookingDate.now();
    booking = buildBooking();
  }

  private static void setBookingOnSameWeek() {
    arrivalDate = BookingDate.now().plusDays(1);
    booking = buildBooking();
  }

  private static void setOlderBooking() {
    arrivalDate = BookingDate.now().plusDays(MINIMUM_DAYS_FOR_FULL_REFUND);
    booking = buildBooking();
  }

  @Test
  public void cancel_withCanceledBooking_shouldThrowBookingAlreadyCanceledException() {
    booking.cancel();

    assertThrows(
        BookingAlreadyCanceledException.class, () -> cancelationService.cancel(booking, bedOwner));
  }

  @Test
  public void cancel_withBookingOnSameDay_shouldThrowCancelationNotAllowedException() {
    setBookingOnSameDay();

    assertThrows(
        CancelationNotAllowedException.class, () -> cancelationService.cancel(booking, bedOwner));
  }

  @Test
  public void cancel_withBookingOnSameWeek_shouldAddStayCanceledTransactions() {
    setBookingOnSameWeek();

    cancelationService.cancel(booking, bedOwner);

    verify(transactionService)
        .addStayCanceledHalfRefund(
            eq(booking.getTenantPublicKey().getValue()),
            eq(bedOwner),
            eq(tenantRefund),
            eq(ownerRefund),
            eq(total),
            eq(booking.getNumberOfNights()));
  }

  @Test
  public void cancel_withOlderBooking_shouldAddStayCanceledTransactions() {
    setOlderBooking();

    cancelationService.cancel(booking, bedOwner);

    verify(transactionService)
        .addStayCanceledFullRefund(
            eq(booking.getTenantPublicKey().getValue()),
            eq(bedOwner),
            eq(total),
            eq(booking.getNumberOfNights()));
  }

  @Test
  public void cancel_shouldCancelBooking() {
    booking = mock(Booking.class);
    when(booking.getTenantPublicKey()).thenReturn(createTenantPublicKey());
    when(booking.getArrivalDate()).thenReturn(arrivalDate);
    when(booking.getTotal()).thenReturn(total);

    cancelationService.cancel(booking, bedOwner);

    verify(booking).cancel();
  }

  @Test
  public void cancel_withBookingOnSameWeek_shouldMapCancelationToResponse() {
    setBookingOnSameWeek();

    CancelationResponse actualCancelationResponse = cancelationService.cancel(booking, bedOwner);

    assertSame(cancelationResponse, actualCancelationResponse);
  }

  @Test
  public void cancel_withOlderBooking_shouldMapCancelationToResponse() {
    setOlderBooking();

    CancelationResponse actualCancelationResponse = cancelationService.cancel(booking, bedOwner);

    assertSame(cancelationResponse, actualCancelationResponse);
  }
}
