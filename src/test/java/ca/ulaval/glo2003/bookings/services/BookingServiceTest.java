package ca.ulaval.glo2003.bookings.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.*;
import static ca.ulaval.glo2003.bookings.rest.helpers.BookingRequestBuilder.aBookingRequest;
import static ca.ulaval.glo2003.bookings.rest.helpers.BookingResponseBuilder.aBookingResponse;
import static ca.ulaval.glo2003.bookings.rest.helpers.CancelationResponseBuilder.aCancelationResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedNumber;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.bookings.converters.BookingConverter;
import ca.ulaval.glo2003.bookings.converters.BookingNumberConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookingServiceTest {

  private static BookingService bookingService;
  private static BedService bedService = mock(BedService.class);
  private static CancelationService cancelationService = mock(CancelationService.class);
  private static TransactionService transactionService = mock(TransactionService.class);
  private static BookingConverter bookingConverter = mock(BookingConverter.class);
  private static BookingNumberConverter bookingNumberConverter = mock(BookingNumberConverter.class);
  private static BookingFactory bookingFactory = mock(BookingFactory.class);
  private static BookingTotalCalculator bookingTotalCalculator = mock(BookingTotalCalculator.class);

  private static BedNumber bedNumber = createBedNumber();
  private static Bed bed = buildBed();
  private static PublicKey ownerPublicKey = createOwnerPublicKey();
  private static UUID bookingNumber = createBookingNumber();
  private static Booking booking = buildBooking();
  private static PublicKey tenantPublicKey = createTenantPublicKey();
  private static BookingDate departureDate = createArrivalDate();
  private static BookingRequest bookingRequest = aBookingRequest().build();
  private static BookingResponse bookingResponse = aBookingResponse().build();
  private static CancelationResponse cancelationResponse = aCancelationResponse().build();
  private static Price total = createTotal();

  @BeforeAll
  public static void setUpService() {
    bookingService =
        new BookingService(
            bookingConverter,
            bookingNumberConverter,
            bookingFactory,
            bookingTotalCalculator,
            transactionService,
            bedService,
            cancelationService);
  }

  private void setUpMocksForAdd() {
    resetMocks();
    when(bedService.get(bedNumber.toString())).thenReturn(bed);
    when(bookingConverter.fromRequest(bookingRequest)).thenReturn(booking);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);
    when(bookingNumberConverter.toString(bookingNumber)).thenReturn(bookingNumber.toString());
  }

  private void setUpMocksForGetResponse() {
    resetMocks();
    when(bed.getBookingByNumber(bookingNumber)).thenReturn(booking);
    when(bookingNumberConverter.fromString(bookingNumber.toString())).thenReturn(bookingNumber);
    when(bedService.get(bedNumber.toString())).thenReturn(bed);
    when(bookingConverter.toResponse(booking)).thenReturn(bookingResponse);
  }

  private void setUpMocksForCancel() {
    resetMocks();
    when(bed.getBookingByNumber(bookingNumber)).thenReturn(booking);
    when(bookingNumberConverter.fromString(bookingNumber.toString())).thenReturn(bookingNumber);
    when(bedService.get(bedNumber.toString())).thenReturn(bed);
    when(cancelationService.cancel(booking, bed.getOwnerPublicKey().toString()))
        .thenReturn(cancelationResponse);
  }

  private void resetMocks() {
    bed = buildBed();
    booking = buildBooking();
    reset(bedService, transactionService, bookingFactory);
  }

  private static Bed buildBed() {
    Bed bed = mock(Bed.class);
    when(bed.getNumber()).thenReturn(bedNumber);
    when(bed.getOwnerPublicKey()).thenReturn(ownerPublicKey);
    return bed;
  }

  private static Booking buildBooking() {
    Booking booking = mock(Booking.class);
    when(booking.getNumber()).thenReturn(bookingNumber);
    when(booking.getTenantPublicKey()).thenReturn(tenantPublicKey);
    when(booking.getDepartureDate()).thenReturn(departureDate);
    return booking;
  }

  @Test
  public void add_shouldUpdateBedInRepository() {
    setUpMocksForAdd();

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(bedService).update(bed);
  }

  @Test
  public void add_shouldReturnBookingNumber() {
    setUpMocksForAdd();

    String actualBookingNumber = bookingService.add(bedNumber.toString(), bookingRequest);

    assertEquals(bookingNumber.toString(), actualBookingNumber);
  }

  @Test
  public void add_shouldBookToBed() {
    setUpMocksForAdd();

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(bed).book(booking);
  }

  @Test
  public void add_shouldAddStayBookedTransaction() {
    setUpMocksForAdd();

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(transactionService).addStayBooked(booking.getTenantPublicKey().toString(), total);
  }

  @Test
  public void add_shouldAddStayCompletedTransaction() {
    setUpMocksForAdd();

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(transactionService)
        .addStayCompleted(
            bed.getOwnerPublicKey().toString(), total, booking.getDepartureDate().toTimestamp());
  }

  @Test
  public void getResponse_shouldGetBookingResponse() {
    setUpMocksForGetResponse();

    BookingResponse bookingResponse =
        bookingService.getResponse(bedNumber.toString(), bookingNumber.toString());

    assertEquals(bookingResponse, bookingResponse);
  }

  @Test
  public void cancel_shouldCancelBooking() {
    setUpMocksForCancel();

    CancelationResponse actualCancelationResponse =
        bookingService.cancel(bedNumber.toString(), bookingNumber.toString());

    assertEquals(cancelationResponse, actualCancelationResponse);
  }
}
