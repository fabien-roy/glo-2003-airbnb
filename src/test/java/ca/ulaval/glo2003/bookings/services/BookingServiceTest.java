package ca.ulaval.glo2003.bookings.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createBookingNumber;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createTotal;
import static ca.ulaval.glo2003.bookings.rest.helpers.BookingRequestBuilder.aBookingRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.converters.BedNumberConverter;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.bookings.converters.BookingConverter;
import ca.ulaval.glo2003.bookings.converters.BookingNumberConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingServiceTest {

  private static BookingService bookingService;
  private static BookingConverter bookingConverter = mock(BookingConverter.class);
  private static BookingNumberConverter bookingNumberConverter = mock(BookingNumberConverter.class);
  private static BedNumberConverter bedNumberConverter = mock(BedNumberConverter.class);
  private static BedRepository bedRepository = mock(BedRepository.class);
  private static BookingFactory bookingFactory = mock(BookingFactory.class);
  private static BookingTotalCalculator bookingTotalCalculator = mock(BookingTotalCalculator.class);
  private static TransactionService transactionService = mock(TransactionService.class);

  private UUID bedNumber = createBedNumber();
  private Bed bed = aBed().withBedNumber(bedNumber).build();
  private UUID bookingNumber = createBookingNumber();
  private Booking booking =
      aBooking()
          .withBookingNumber(bookingNumber)
          .withPackage(bed.getPricesPerNight().keySet().iterator().next())
          .build();
  private BookingRequest bookingRequest = aBookingRequest().build();
  private Price total = createTotal();

  @BeforeAll
  public static void setUpService() {
    bookingService =
        new BookingService(
            bookingConverter,
            bookingNumberConverter,
            bedNumberConverter,
            bedRepository,
            bookingFactory,
            bookingTotalCalculator,
            transactionService);
  }

  @BeforeEach
  public void setUpMocksForAdd() {
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);
    when(bookingNumberConverter.toString(bookingNumber)).thenReturn(bookingNumber.toString());
  }

  @BeforeEach
  public void setUpMocksForGetByNumber() {
    when(bookingNumberConverter.fromString(bookingNumber.toString())).thenReturn(bookingNumber);
    when(bedNumberConverter.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingConverter.fromRequest(bookingRequest)).thenReturn(booking);
  }

  @Test
  public void add_shouldUpdateBedInRepository() {
    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(bedRepository).update(bed);
  }

  @Test
  public void add_shouldReturnBookingNumber() {
    booking.setNumber(bookingNumber);

    String actualBookingNumber = bookingService.add(bedNumber.toString(), bookingRequest);

    assertEquals(bookingNumber.toString(), actualBookingNumber);
  }

  @Test
  public void add_shouldBookToBed() {
    bookingService.add(bedNumber.toString(), bookingRequest);
    List<Booking> bookings = bed.getBookings();

    assertTrue(bookings.contains(booking));
  }

  @Test
  public void add_shouldAddStayBookedTransaction() {
    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(transactionService).addStayBooked(booking.getTenantPublicKey().getValue(), total);
  }

  @Test
  public void add_shouldAddStayCompletedTransaction() {
    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(transactionService)
        .addStayCompleted(bed.getOwnerPublicKey().getValue(), total, booking.getNumberOfNights());
  }

  @Test
  public void getByNumber_shouldGetBooking() {
    bed.book(booking);
    BookingResponse expectedBookingResponse = mock(BookingResponse.class);
    when(bookingConverter.toResponse(booking)).thenReturn(expectedBookingResponse);

    BookingResponse bookingResponse =
        bookingService.getByNumber(bedNumber.toString(), bookingNumber.toString());

    assertEquals(expectedBookingResponse, bookingResponse);
  }
}
