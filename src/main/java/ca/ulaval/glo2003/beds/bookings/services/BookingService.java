package ca.ulaval.glo2003.beds.bookings.services;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.beds.bookings.rest.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.rest.BookingResponse;
import ca.ulaval.glo2003.beds.bookings.rest.CancelResponse;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import ca.ulaval.glo2003.transactions.domain.Price;
import ca.ulaval.glo2003.transactions.services.TransactionService;
import java.awt.*;
import java.util.UUID;

public class BookingService {

  private final TransactionService transactionService;
  private final BookingMapper bookingMapper;
  private final BedRepository bedRepository;
  private final BookingFactory bookingFactory;
  private final BookingTotalCalculator bookingTotalCalculator;
  private final BookingNumberMapper bookingNumberMapper;
  private final BedNumberMapper bedNumberMapper;

  public BookingService(
      TransactionService transactionService,
      BookingMapper bookingMapper,
      BedRepository bedRepository,
      BookingFactory bookingFactory,
      BookingTotalCalculator bookingTotalCalculator,
      BedNumberMapper bedNumberMapper,
      BookingNumberMapper bookingNumberMapper) {
    this.transactionService = transactionService;
    this.bookingMapper = bookingMapper;
    this.bedRepository = bedRepository;
    this.bookingFactory = bookingFactory;
    this.bookingTotalCalculator = bookingTotalCalculator;
    this.bedNumberMapper = bedNumberMapper;
    this.bookingNumberMapper = bookingNumberMapper;
  }

  public String add(String bedNumber, BookingRequest bookingRequest) {
    UUID parsedBedNumber = bedNumberMapper.fromString(bedNumber);
    Booking booking = bookingMapper.fromRequest(bookingRequest);
    Bed bed = bedRepository.getByNumber(parsedBedNumber);
    Price total = bookingTotalCalculator.calculateTotal(bed, booking);
    booking = bookingFactory.create(booking, total);
    transactionService.addStayBooked(booking.getTenantPublicKey().getValue(), total);
    transactionService.addStayCompleted(
        bed.getOwnerPublicKey().getValue(), total, booking.getNumberOfNights());
    bed.book(booking);
    bedRepository.update(bed);
    return booking.getNumber().toString();
  }

  public BookingResponse getByNumber(String bedNumber, String bookingNumber) {
    UUID parsedBedNumber = bedNumberMapper.fromString(bedNumber);
    UUID parsedBookingNumber = bookingNumberMapper.fromString(bookingNumber);

    Bed bed = bedRepository.getByNumber(parsedBedNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return bookingMapper.toResponse(booking);
  }

  public CancelResponse cancel(String bedNumber, String bookingNumber) {
    CancelResponse cancelResponse = new CancelResponse();

    return cancelResponse;
  }
}
