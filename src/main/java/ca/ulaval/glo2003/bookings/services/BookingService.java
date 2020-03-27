package ca.ulaval.glo2003.bookings.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.bookings.mappers.BookingMapper;
import ca.ulaval.glo2003.bookings.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.UUID;
import javax.inject.Inject;

public class BookingService {

  private final BedService bedService;
  private final CancelationService cancelationService;
  private final TransactionService transactionService;
  private final BookingMapper bookingMapper;
  private final BookingFactory bookingFactory;
  private final BookingTotalCalculator bookingTotalCalculator;
  private final BookingNumberMapper bookingNumberMapper;

  @Inject
  public BookingService(
      BedService bedService,
      CancelationService cancelationService,
      TransactionService transactionService,
      BookingMapper bookingMapper,
      BookingFactory bookingFactory,
      BookingTotalCalculator bookingTotalCalculator,
      BookingNumberMapper bookingNumberMapper) {
    this.bedService = bedService;
    this.cancelationService = cancelationService;
    this.transactionService = transactionService;
    this.bookingMapper = bookingMapper;
    this.bookingFactory = bookingFactory;
    this.bookingTotalCalculator = bookingTotalCalculator;
    this.bookingNumberMapper = bookingNumberMapper;
  }

  public String add(String bedNumber, BookingRequest bookingRequest) {
    Bed bed = bedService.get(bedNumber);
    Booking booking = bookingMapper.fromRequest(bookingRequest);
    Price total = bookingTotalCalculator.calculateTotal(bed, booking);
    booking = bookingFactory.create(booking, total);
    bed.book(booking);
    transactionService.addStayBooked(booking.getTenantPublicKey().getValue(), total);
    transactionService.addStayCompleted(
        bed.getOwnerPublicKey().getValue(), total, booking.getNumberOfNights());
    bedService.update(bed);
    return booking.getNumber().toString();
  }

  public BookingResponse getResponse(String bedNumber, String bookingNumber) {
    Bed bed = bedService.get(bedNumber);
    UUID parsedBookingNumber = bookingNumberMapper.fromString(bookingNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return bookingMapper.toResponse(booking);
  }

  public CancelationResponse cancel(String bedNumber, String bookingNumber) {
    Bed bed = bedService.get(bedNumber);
    UUID parsedBookingNumber = bookingNumberMapper.fromString(bookingNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return cancelationService.cancel(booking, bed.getOwnerPublicKey().toString());
  }
}
