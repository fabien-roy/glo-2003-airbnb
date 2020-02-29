package ca.ulaval.glo2003.beds.bookings.services;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingNumberMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingResponse;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import java.util.UUID;

public class BookingService {

  private final TransactionFactory transactionFactory;
  private final BedRepository bedRepository;
  private final BookingMapper bookingMapper;
  private final BookingNumberMapper bookingNumberMapper;
  private final BedNumberMapper bedNumberMapper;

  public BookingService(
      TransactionFactory transactionFactory,
      BedRepository bedRepository,
      BookingMapper bookingMapper,
      BedNumberMapper bedNumberMapper,
      BookingNumberMapper bookingNumberMapper) {
    this.transactionFactory = transactionFactory;
    this.bedRepository = bedRepository;
    this.bookingMapper = bookingMapper;
    this.bedNumberMapper = bedNumberMapper;
    this.bookingNumberMapper = bookingNumberMapper;
  }

  // TODO : Add BookingRequest to parameters
  public String add(UUID bedNumber, BedRequest bedRequest) {
    // TODO : map booking via bookingMapper
    // TODO : get beds via BedRepository
    // TODO : Bed.book(booking)
    // TODO : add bookingNumber with BookingFactory.create(booking)
    // TODO : Get total with BookingTotalCalculator
    // TODO : Create both transactions using TransactionFactory
    // TODO : Add transactions to booking
    // TODO : Save bed in repository (TEST)
    return null; // TODO : Return new booking number (TEST)
  }

  public BookingResponse getByNumber(String bedNumber, String bookingNumber) {
    UUID parsedBedNumber = bedNumberMapper.fromString(bedNumber);
    UUID parsedBookingNumber = bookingNumberMapper.fromString(bookingNumber);

    Bed bed = bedRepository.getByNumber(parsedBedNumber);

    Booking booking = bed.getBookingByNumber(parsedBookingNumber);

    return bookingMapper.toResponse(booking);
  }
}
