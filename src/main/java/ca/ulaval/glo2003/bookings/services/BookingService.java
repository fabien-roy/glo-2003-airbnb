package ca.ulaval.glo2003.bookings.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.bookings.converters.BookingConverter;
import ca.ulaval.glo2003.bookings.converters.BookingNumberConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.UUID;
import javax.inject.Inject;

public class BookingService {

  private final BookingConverter bookingConverter;
  private final BookingNumberConverter bookingNumberConverter;
  private final BookingFactory bookingFactory;
  private final BookingTotalCalculator bookingTotalCalculator;
  private final TransactionService transactionService;
  private final BedService bedService;
  private final CancelationService cancelationService;

  @Inject
  public BookingService(
      BookingConverter bookingConverter,
      BookingNumberConverter bookingNumberConverter,
      BookingFactory bookingFactory,
      BookingTotalCalculator bookingTotalCalculator,
      TransactionService transactionService,
      BedService bedService,
      CancelationService cancelationService) {
    this.bookingConverter = bookingConverter;
    this.bookingNumberConverter = bookingNumberConverter;
    this.bookingFactory = bookingFactory;
    this.bookingTotalCalculator = bookingTotalCalculator;
    this.transactionService = transactionService;
    this.bedService = bedService;
    this.cancelationService = cancelationService;
  }

  public String add(String bedNumber, BookingRequest bookingRequest) {
    Bed bed = bedService.get(bedNumber);
    Booking booking = bookingConverter.fromRequest(bookingRequest);
    Price total = bookingTotalCalculator.calculateTotal(bed, booking);
    booking = bookingFactory.create(booking, total);
    bed.book(booking);
    transactionService.addStayBooked(booking.getTenantPublicKey().getValue(), total);
    transactionService.addStayCompleted(
        bed.getOwnerPublicKey().getValue(), total, booking.getDepartureDate().toTimestamp());
    bedService.update(bed);
    return bookingNumberConverter.toString(booking.getNumber());
  }

  public BookingResponse getResponse(String bedNumber, String bookingNumber) {
    Bed bed = bedService.get(bedNumber);
    UUID parsedBookingNumber = bookingNumberConverter.fromString(bookingNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return bookingConverter.toResponse(booking);
  }

  public CancelationResponse cancel(String bedNumber, String bookingNumber) {
    Bed bed = bedService.get(bedNumber);
    UUID parsedBookingNumber = bookingNumberConverter.fromString(bookingNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return cancelationService.cancel(booking, bed.getOwnerPublicKey().toString());
  }
}
