package ca.ulaval.glo2003.bookings.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.bookings.converters.BookingConverter;
import ca.ulaval.glo2003.bookings.converters.BookingNumberConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingNumber;
import ca.ulaval.glo2003.bookings.domain.BookingPriceCalculator;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.reports.services.ReportService;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import javax.inject.Inject;

public class BookingService {

  private final BookingConverter bookingConverter;
  private final BookingNumberConverter bookingNumberConverter;
  private final BookingFactory bookingFactory;
  private final BookingPriceCalculator bookingPriceCalculator;
  private final TransactionService transactionService;
  private final BedService bedService;
  private final CancelationService cancelationService;
  private final ReportService reportService;

  @Inject
  public BookingService(
      BookingConverter bookingConverter,
      BookingNumberConverter bookingNumberConverter,
      BookingFactory bookingFactory,
      BookingPriceCalculator bookingPriceCalculator,
      TransactionService transactionService,
      BedService bedService,
      CancelationService cancelationService,
      ReportService reportService) {
    this.bookingConverter = bookingConverter;
    this.bookingNumberConverter = bookingNumberConverter;
    this.bookingFactory = bookingFactory;
    this.bookingPriceCalculator = bookingPriceCalculator;
    this.transactionService = transactionService;
    this.bedService = bedService;
    this.cancelationService = cancelationService;
    this.reportService = reportService;
  }

  public String add(String bedNumber, BookingRequest bookingRequest) {
    Bed bed = bedService.get(bedNumber);
    Booking booking = bookingConverter.fromRequest(bookingRequest);
    Price price = bookingPriceCalculator.calculatePrice(bed, booking);
    booking = bookingFactory.create(booking, price);
    bed.book(booking);
    addTransactions(bed, booking);
    reportService.addReservation(bed, booking);
    bedService.update(bed);
    return booking.getNumber().toString();
  }

  private void addTransactions(Bed bed, Booking booking) {
    transactionService.addStayBooked(booking.getTenantPublicKey().toString(), booking.getTotal());
    transactionService.addStayCompleted(
        bed.getOwnerPublicKey().toString(),
        booking.getTotal(),
        booking.getDepartureDate().toTimestamp());
  }

  public BookingResponse getResponse(String bedNumber, String bookingNumber) {
    Bed bed = bedService.get(bedNumber);
    BookingNumber parsedBookingNumber = bookingNumberConverter.fromString(bookingNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return bookingConverter.toResponse(booking);
  }

  public CancelationResponse cancel(String bedNumber, String bookingNumber) {
    Bed bed = bedService.get(bedNumber);
    BookingNumber parsedBookingNumber = bookingNumberConverter.fromString(bookingNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return cancelationService.cancel(bed, booking);
  }
}
