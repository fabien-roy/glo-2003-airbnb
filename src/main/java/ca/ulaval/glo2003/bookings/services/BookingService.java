package ca.ulaval.glo2003.bookings.services;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.mappers.BedNumberConverter;
import ca.ulaval.glo2003.bookings.domain.Booking;
import ca.ulaval.glo2003.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.bookings.mappers.BookingMapper;
import ca.ulaval.glo2003.bookings.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.bookings.rest.CancelResponse;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.util.UUID;
import javax.inject.Inject;

public class BookingService {

  private final TransactionService transactionService;
  private final BookingMapper bookingMapper;
  private final BedRepository bedRepository;
  private final BookingFactory bookingFactory;
  private final BookingTotalCalculator bookingTotalCalculator;
  private final BookingNumberMapper bookingNumberMapper;
  private final BedNumberConverter bedNumberConverter;

  @Inject
  public BookingService(
      TransactionService transactionService,
      BookingMapper bookingMapper,
      BedRepository bedRepository,
      BookingFactory bookingFactory,
      BookingTotalCalculator bookingTotalCalculator,
      BedNumberConverter bedNumberConverter,
      BookingNumberMapper bookingNumberMapper) {
    this.transactionService = transactionService;
    this.bookingMapper = bookingMapper;
    this.bedRepository = bedRepository;
    this.bookingFactory = bookingFactory;
    this.bookingTotalCalculator = bookingTotalCalculator;
    this.bedNumberConverter = bedNumberConverter;
    this.bookingNumberMapper = bookingNumberMapper;
  }

  public String add(String bedNumber, BookingRequest bookingRequest) {
    UUID parsedBedNumber = bedNumberConverter.fromString(bedNumber);
    Booking booking = bookingMapper.fromRequest(bookingRequest);
    Bed bed = bedRepository.getByNumber(parsedBedNumber);
    Price total = bookingTotalCalculator.calculateTotal(bed, booking);
    booking = bookingFactory.create(booking, total);
    bed.book(booking);
    transactionService.addStayBooked(booking.getTenantPublicKey().getValue(), total);
    transactionService.addStayCompleted(
        bed.getOwnerPublicKey().getValue(), total, booking.getNumberOfNights());
    bedRepository.update(bed);
    return booking.getNumber().toString();
  }

  public BookingResponse getByNumber(String bedNumber, String bookingNumber) {
    UUID parsedBedNumber = bedNumberConverter.fromString(bedNumber);
    UUID parsedBookingNumber = bookingNumberMapper.fromString(bookingNumber);

    Bed bed = bedRepository.getByNumber(parsedBedNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return bookingMapper.toResponse(booking);
  }

  public CancelResponse cancel(String bedNumber, String bookingNumber) {
    return new CancelResponse();
  }
}
