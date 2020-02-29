package ca.ulaval.glo2003.beds.bookings.services;

import ca.ulaval.glo2003.beds.bookings.domain.Booking;
import ca.ulaval.glo2003.beds.bookings.domain.BookingFactory;
import ca.ulaval.glo2003.beds.bookings.domain.BookingTotalCalculator;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingMapper;
import ca.ulaval.glo2003.beds.bookings.rest.mappers.BookingRequest;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.Price;
import java.util.UUID;

public class BookingService {

  private final TransactionFactory transactionFactory;
  private final BookingMapper bookingMapper;
  private final BedRepository bedRepository;
  private final BookingFactory bookingFactory;
  private final BookingTotalCalculator bookingTotalCalculator;

  private static final String temporaryUser = "Fabien";
  private static final String temporaryOwner = "Samuel CC";
  // TODO replace this with real users when the object exists

  public BookingService(
      TransactionFactory transactionFactory,
      BookingMapper bookingMapper,
      BedRepository bedRepository,
      BookingFactory bookingFactory,
      BookingTotalCalculator bookingTotalCalculator) {
    this.transactionFactory = transactionFactory;
    this.bookingMapper = bookingMapper;
    this.bedRepository = bedRepository;
    this.bookingFactory = bookingFactory;
    this.bookingTotalCalculator = bookingTotalCalculator;
  }

  public String add(UUID bedNumber, BookingRequest bookingRequest) {
    Booking booking = bookingMapper.fromRequest(bookingRequest);
    Bed bed = bedRepository.getByNumber(bedNumber);
    booking = bookingFactory.create(booking);
    Price total = bookingTotalCalculator.calculateTotal(bed, booking);
    Transaction transactionBooked = transactionFactory.createStayBooked(temporaryUser, total);
    Transaction transactionCompleted =
        transactionFactory.createStayCompleted(temporaryOwner, total, booking.getNumberOfNights());
    booking.getTransactions().add(transactionBooked);
    booking.getTransactions().add(transactionCompleted);
    bedRepository.add(bed);
    return booking.getNumber().toString();
  }

  public void getByNumber(String number) {
    // TODO
  }
}
