package ca.ulaval.glo2003.beds.bookings.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingServiceTest {

  private BookingService bookingService;
  private TransactionFactory transactionFactory;
  private BookingMapper bookingMapper;
  private BedRepository bedRepository;
  private BookingFactory bookingFactory;

  @BeforeEach
  public void setUpService() {
    transactionFactory = mock(TransactionFactory.class);
    bookingMapper = mock(BookingMapper.class);
    bedRepository = mock(BedRepository.class);
    bookingFactory = mock(BookingFactory.class);
    bookingService =
        new BookingService(transactionFactory, bookingMapper, bedRepository, bookingFactory);
  }

  @Test
  public void add_withBookingRequest_shouldMapRequest() {
    UUID bedNumber = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(expectedBooking.getNumber()).thenReturn(bedNumber);

    bookingService.add(bedNumber, bookingRequest);

    verify(bookingMapper).fromRequest(bookingRequest);
  }

  @Test
  public void add_withBookingRequest_shouldGetBedFromBedRepository() {
    UUID bedNumber = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(expectedBooking.getNumber()).thenReturn(bedNumber);

    bookingService.add(bedNumber, bookingRequest);

    verify(bedRepository).getByNumber(bedNumber);
  }

  @Test
  public void add_withBookingRequest_shouldCreateBookingWithBookingFactory() {
    UUID bedNumber = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(expectedBooking.getNumber()).thenReturn(bedNumber);

    bookingService.add(bedNumber, bookingRequest);

    verify(bookingFactory).create(expectedBooking);
  }

  @Test
  public void add_withBookingRequest_shouldSaveBedToBedRepository() {
    UUID bedNumber = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(expectedBooking.getNumber()).thenReturn(bedNumber);

    bookingService.add(bedNumber, bookingRequest);

    verify(bedRepository).add(bedOfBooking);
  }

  @Test
  public void add_withBookingRequest_shouldReturnBookingNumber() {
    UUID bedNumber = mock(UUID.class);
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bedOfBooking = mock(Bed.class);
    Booking expectedBooking = mock(Booking.class);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(expectedBooking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bedOfBooking);
    when(bookingFactory.create(expectedBooking)).thenReturn(expectedBooking);
    when(expectedBooking.getNumber()).thenReturn(bedNumber);

    String bookingNumber = bookingService.add(bedNumber, bookingRequest);

    assertEquals(expectedBooking.getNumber().toString(), bookingNumber);
  }
}
