package ca.ulaval.glo2003.beds.bookings.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingResponse;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingServiceTest {

  private BookingService bookingService;
  private TransactionFactory transactionFactory;
  private BookingMapper bookingMapper;
  private BedRepository bedRepository;
  private BookingFactory bookingFactory;
  private BookingTotalCalculator bookingTotalCalculator;
  private BedNumberMapper bedNumberMapper;
  private BookingNumberMapper bookingNumberMapper;

  private static final String TENANT = "a tenant";
  private static final String OWNER = "an owner";

  @BeforeEach
  public void setUpService() {
    transactionFactory = mock(TransactionFactory.class);
    bookingMapper = mock(BookingMapper.class);
    bedRepository = mock(BedRepository.class);
    bookingFactory = mock(BookingFactory.class);
    bookingTotalCalculator = mock(BookingTotalCalculator.class);
    bedNumberMapper = mock(BedNumberMapper.class);
    bookingNumberMapper = mock(BookingNumberMapper.class);
    bookingService =
        new BookingService(
            transactionFactory,
            bookingMapper,
            bedRepository,
            bookingFactory,
            bookingTotalCalculator,
            bedNumberMapper,
            bookingNumberMapper);
  }

  @Test
  public void add_shouldSaveBedToBedRepository() {
    String bedNumber = UUID.randomUUID().toString();
    UUID bedNumberId = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    Price priceOfBooking = mock(Price.class);
    Transaction transaction = mock(Transaction.class);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(UUID.fromString(bedNumber))).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(bookingTotalCalculator.calculateTotal(bedOfBooking, expectedBooking))
        .thenReturn(priceOfBooking);
    when(transactionFactory.createStayBooked(TENANT, priceOfBooking)).thenReturn(transaction);
    when(transactionFactory.createStayCompleted(
            OWNER, priceOfBooking, expectedBooking.getNumberOfNights()))
        .thenReturn(transaction);
    when(expectedBooking.getNumber()).thenReturn(bedNumberId);

    bookingService.add(bedNumber, bookingRequest);

    verify(bedRepository).add(bedOfBooking);
  }

  @Test
  public void add_shouldReturnBookingNumber() {
    String bedNumber = UUID.randomUUID().toString();
    UUID bedNumberId = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    Price priceOfBooking = mock(Price.class);
    Transaction transaction = mock(Transaction.class);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(UUID.fromString(bedNumber))).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(bookingTotalCalculator.calculateTotal(bedOfBooking, expectedBooking))
        .thenReturn(priceOfBooking);
    when(transactionFactory.createStayBooked(TENANT, priceOfBooking)).thenReturn(transaction);
    when(transactionFactory.createStayCompleted(
            OWNER, priceOfBooking, expectedBooking.getNumberOfNights()))
        .thenReturn(transaction);
    when(expectedBooking.getNumber()).thenReturn(bedNumberId);

    String bookingNumber = bookingService.add(bedNumber, bookingRequest);

    assertEquals(expectedBooking.getNumber().toString(), bookingNumber);
  }

  @Test
  public void add_shouldAddTransactionsToBooking() {
    String bedNumber = UUID.randomUUID().toString();
    UUID bedNumberId = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    Price priceOfBooking = mock(Price.class);
    Transaction transaction = mock(Transaction.class);
    Transaction otherTransaction = mock(Transaction.class);
    List<Transaction> transactions = new ArrayList<>();
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(UUID.fromString(bedNumber))).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(bookingTotalCalculator.calculateTotal(bedOfBooking, expectedBooking))
        .thenReturn(priceOfBooking);
    when(transactionFactory.createStayBooked(TENANT, priceOfBooking)).thenReturn(transaction);
    when(transactionFactory.createStayCompleted(
            OWNER, priceOfBooking, expectedBooking.getNumberOfNights()))
        .thenReturn(otherTransaction);
    when(expectedBooking.getTransactions()).thenReturn(transactions);
    when(expectedBooking.getNumber()).thenReturn(bedNumberId);

    bookingService.add(bedNumber, bookingRequest);

    assertEquals(2, transactions.size());
    verify(expectedBooking, times(2)).getTransactions();
  }

  @Test
  public void getByNumber_withNumber_shouldGetBooking() {
    String requestedBedNumber = "requestedBedNumber";
    String requestedBookingNumber = "requestedBookingNumber";
    UUID bedNumber = mock(UUID.class);
    UUID bookingNumber = mock(UUID.class);
    when(bedNumberMapper.fromString(requestedBedNumber)).thenReturn(bedNumber);
    when(bookingNumberMapper.fromString(requestedBookingNumber)).thenReturn(bookingNumber);
    Bed bed = mock(Bed.class);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    Booking booking = mock(Booking.class);
    when(bed.getBookingByNumber(bookingNumber)).thenReturn(booking);
    BookingResponse expectedBookingResponse = mock(BookingResponse.class);
    when(bookingMapper.toResponse(booking)).thenReturn(expectedBookingResponse);

    BookingResponse bookingResponse =
        bookingService.getByNumber(requestedBedNumber, requestedBookingNumber);

    assertEquals(expectedBookingResponse, bookingResponse);
  }
}
