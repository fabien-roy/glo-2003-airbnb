package ca.ulaval.glo2003.bookings.services;

import ca.ulaval.glo2003.beds.converters.BedNumberConverter;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.bookings.mappers.BookingConverter;
import ca.ulaval.glo2003.bookings.mappers.BookingNumberConverter;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.bookings.rest.CancelResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.UUID;
import javax.inject.Inject;

public class BookingService {

  private final BookingConverter bookingConverter;
  private final BookingNumberConverter bookingNumberConverter;
  private final BedNumberConverter bedNumberConverter;
  private final BedRepository bedRepository;
  private final BookingFactory bookingFactory;
  private final BookingTotalCalculator bookingTotalCalculator;
  private final TransactionService transactionService;

  @Inject
  public BookingService(
      BookingConverter bookingConverter,
      BookingNumberConverter bookingNumberConverter,
      BedNumberConverter bedNumberConverter,
      BedRepository bedRepository,
      BookingFactory bookingFactory,
      BookingTotalCalculator bookingTotalCalculator,
      TransactionService transactionService) {
    this.bookingConverter = bookingConverter;
    this.bookingNumberConverter = bookingNumberConverter;
    this.bedNumberConverter = bedNumberConverter;
    this.bedRepository = bedRepository;
    this.bookingFactory = bookingFactory;
    this.bookingTotalCalculator = bookingTotalCalculator;
    this.transactionService = transactionService;
  }

  public String add(String bedNumber, BookingRequest bookingRequest) {
    UUID parsedBedNumber = bedNumberConverter.fromString(bedNumber);
    Booking booking = bookingConverter.fromRequest(bookingRequest);
    Bed bed = bedRepository.getByNumber(parsedBedNumber);
    Price total = bookingTotalCalculator.calculateTotal(bed, booking);
    booking = bookingFactory.create(booking, total);
    bed.book(booking);
    transactionService.addStayBooked(booking.getTenantPublicKey().getValue(), total);
    transactionService.addStayCompleted(
        bed.getOwnerPublicKey().getValue(), total, booking.getNumberOfNights());
    bedRepository.update(bed);
    return bookingNumberConverter.toString(booking.getNumber());
  }

  public BookingResponse getByNumber(String bedNumber, String bookingNumber) {
    UUID parsedBedNumber = bedNumberConverter.fromString(bedNumber);
    UUID parsedBookingNumber = bookingNumberConverter.fromString(bookingNumber);

    Bed bed = bedRepository.getByNumber(parsedBedNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return bookingConverter.toResponse(booking);
  }

  public CancelResponse cancel(String bedNumber, String bookingNumber) {
    return new CancelResponse();
  }
}
