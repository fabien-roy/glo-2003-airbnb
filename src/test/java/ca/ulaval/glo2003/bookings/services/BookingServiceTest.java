package ca.ulaval.glo2003.bookings.services;

import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.*;
import static ca.ulaval.glo2003.bookings.rest.helpers.BookingRequestBuilder.aBookingRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.mappers.BedNumberMapper;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.bookings.mappers.BookingMapper;
import ca.ulaval.glo2003.bookings.mappers.BookingNumberMapper;
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
  private static TransactionService transactionService;
  private static BookingMapper bookingMapper;
  private static BedRepository bedRepository;
  private static BookingFactory bookingFactory;
  private static BookingTotalCalculator bookingTotalCalculator;
  private static BedNumberMapper bedNumberMapper;
  private static BookingNumberMapper bookingNumberMapper;

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
    transactionService = mock(TransactionService.class);
    bookingMapper = mock(BookingMapper.class);
    bedRepository = mock(BedRepository.class);
    bookingFactory = mock(BookingFactory.class);
    bookingTotalCalculator = mock(BookingTotalCalculator.class);
    bedNumberMapper = mock(BedNumberMapper.class);
    bookingNumberMapper = mock(BookingNumberMapper.class);
    bookingService =
        new BookingService(
            transactionService,
            bookingMapper,
            bedRepository,
            bookingFactory,
            bookingTotalCalculator,
            bedNumberMapper,
            bookingNumberMapper);
  }

  @BeforeEach
  public void setUpMocksForAdd() {
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);
  }

  @BeforeEach
  public void setUpMocksForGetByNumber() {
    when(bookingNumberMapper.fromString(bookingNumber.toString())).thenReturn(bookingNumber);
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(booking);
  }

  @Test
  public void add_shouldUpdateBedInRepository() {
    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(bedRepository).update(bed);
  }

  @Test
  public void add_shouldReturnBookingNumber() {
    UUID expectedBookingNumber = createBookingNumber();
    booking.setNumber(expectedBookingNumber);

    String bookingNumber = bookingService.add(bedNumber.toString(), bookingRequest);

    assertEquals(expectedBookingNumber.toString(), bookingNumber);
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
    when(bookingMapper.toResponse(booking)).thenReturn(expectedBookingResponse);

    BookingResponse bookingResponse =
        bookingService.getByNumber(bedNumber.toString(), bookingNumber.toString());

    assertEquals(expectedBookingResponse, bookingResponse);
  }
}
