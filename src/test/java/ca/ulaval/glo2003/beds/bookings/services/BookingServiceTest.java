package ca.ulaval.glo2003.beds.bookings.services;

import static ca.ulaval.glo2003.beds.bookings.helpers.BookingBuilder.aBooking;
import static ca.ulaval.glo2003.beds.bookings.helpers.BookingObjectMother.*;
import static ca.ulaval.glo2003.beds.domain.helpers.BedBuilder.aBed;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createBedNumber;
import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPackageName;
import static ca.ulaval.glo2003.beds.domain.helpers.PackageObjectMother.createPricePerNight;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.beds.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.Price;
import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import java.util.Collections;
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
  public void add_shouldUpdateBedInRepository() {
    UUID bedNumber = createBedNumber();
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bed = aBed().build();
    Booking booking =
        aBooking().withPackage(bed.getPricesPerNight().keySet().iterator().next()).build();
    Price total = createTotal();
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(booking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(bedRepository).update(bed);
  }

  @Test
  public void add_shouldReturnBookingNumber() {
    UUID bedNumber = createBedNumber();
    UUID expectedBookingNumber = createBookingNumber();
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bed = aBed().build();
    Booking booking =
        aBooking()
            .withBookingNumber(expectedBookingNumber)
            .withPackage(bed.getPricesPerNight().keySet().iterator().next())
            .build();
    Price total = createTotal();
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(booking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);

    String bookingNumber = bookingService.add(bedNumber.toString(), bookingRequest);

    assertEquals(expectedBookingNumber.toString(), bookingNumber);
  }

  @Test
  public void add_shouldAddStayBookedTransactionToBooking() {
    UUID bedNumber = createBedNumber();
    BookingRequest bookingRequest = mock(BookingRequest.class);
    PublicKey tenantPublicKey = createTenantPublicKey();
    Price total = createTotal();
    Booking booking = mock(Booking.class);
    Bed bed = aBed().build();
    Transaction stayBookedTransaction = mock(Transaction.class);
    when(booking.getTenantPublicKey()).thenReturn(tenantPublicKey);
    when(booking.getNumber()).thenReturn(createBookingNumber());
    when(booking.getPackage()).thenReturn(bed.getPricesPerNight().keySet().iterator().next());
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(booking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);
    when(transactionFactory.createStayBooked(tenantPublicKey.getValue(), total))
        .thenReturn(stayBookedTransaction);

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(booking).addTransaction(stayBookedTransaction);
  }

  @Test
  public void add_shouldAddStayCompletedTransactionToBooking() {
    UUID bedNumber = createBedNumber();
    BookingRequest bookingRequest = mock(BookingRequest.class);
    PublicKey ownerPublicKey = createOwnerPublicKey();
    int numberOfNights = createNumberOfNights();
    Price total = createTotal();
    Booking booking = mock(Booking.class);
    Bed bed = aBed().withOwnerPublicKey(ownerPublicKey).build();
    Transaction stayCompletedTransaction = mock(Transaction.class);
    when(booking.getNumberOfNights()).thenReturn(numberOfNights);
    when(booking.getNumber()).thenReturn(createBookingNumber());
    when(booking.getPackage()).thenReturn(bed.getPricesPerNight().keySet().iterator().next());
    when(booking.getTenantPublicKey()).thenReturn(createTenantPublicKey());
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(booking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);
    when(transactionFactory.createStayCompleted(ownerPublicKey.getValue(), total, numberOfNights))
        .thenReturn(stayCompletedTransaction);

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(booking).addTransaction(stayCompletedTransaction);
  }

  @Test
  public void add_shouldBookToBed() {
    UUID bedNumber = createBedNumber();
    BookingRequest bookingRequest = mock(BookingRequest.class);
    Bed bed = mock(Bed.class);
    when(bed.getPricesPerNight())
        .thenReturn(Collections.singletonMap(createPackageName(), createPricePerNight()));
    when(bed.getOwnerPublicKey()).thenReturn(mock(PublicKey.class));
    Booking booking =
        aBooking().withPackage(bed.getPricesPerNight().keySet().iterator().next()).build();
    Price total = createTotal();
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingMapper.fromRequest(bookingRequest)).thenReturn(booking);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    when(bookingTotalCalculator.calculateTotal(bed, booking)).thenReturn(total);
    when(bookingFactory.create(booking, total)).thenReturn(booking);

    bookingService.add(bedNumber.toString(), bookingRequest);

    verify(bed).book(booking);
  }

  @Test
  public void getByNumber_withNumber_shouldGetBooking() {
    UUID bedNumber = createBedNumber();
    UUID bookingNumber = createBookingNumber();
    when(bedNumberMapper.fromString(bedNumber.toString())).thenReturn(bedNumber);
    when(bookingNumberMapper.fromString(bookingNumber.toString())).thenReturn(bookingNumber);
    Bed bed = mock(Bed.class);
    when(bedRepository.getByNumber(bedNumber)).thenReturn(bed);
    Booking booking = mock(Booking.class);
    when(bed.getBookingByNumber(bookingNumber)).thenReturn(booking);
    BookingResponse expectedBookingResponse = mock(BookingResponse.class);
    when(bookingMapper.toResponse(booking)).thenReturn(expectedBookingResponse);

    BookingResponse bookingResponse =
        bookingService.getByNumber(bedNumber.toString(), bookingNumber.toString());

    assertEquals(expectedBookingResponse, bookingResponse);
  }
}
