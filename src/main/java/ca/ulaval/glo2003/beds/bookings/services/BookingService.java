package ca.ulaval.glo2003.beds.bookings.services;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import java.util.UUID;

public class BookingService {

  private final TransactionFactory transactionFactory;
  private final BookingMapper bookingMapper;
  private final BedRepository bedRepository;
  private final BookingFactory bookingFactory;

  public BookingService(
      TransactionFactory transactionFactory,
      BookingMapper bookingMapper,
      BedRepository bedRepository,
      BookingFactory bookingFactory) {
    this.transactionFactory = transactionFactory;
    this.bookingMapper = bookingMapper;
    this.bedRepository = bedRepository;
    this.bookingFactory = bookingFactory;
  }

  public String add(UUID bedNumber, BookingRequest bookingRequest) {
    Booking booking = bookingMapper.fromRequest(bookingRequest);
    Bed bed = bedRepository.getByNumber(bedNumber);
    booking = bookingFactory.create(booking);
    // TODO : add bookingNumber with BookingFactory.create(booking)
    // TODO : Get total with BookingTotalCalculator
    // TODO : Create both transactions using TransactionFactory
    // TODO : Add transactions to booking
    bedRepository.add(bed);
    return booking.getNumber().toString();
  }

  public void getByNumber(String number) {
    // TODO
  }
}
