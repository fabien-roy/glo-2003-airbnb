package ca.ulaval.glo2003.beds.bookings.services;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionFactory;
import ca.ulaval.glo2003.beds.rest.BedRequest;
import java.util.UUID;

public class BookingService {

  private final TransactionFactory transactionFactory;

  public BookingService(TransactionFactory transactionFactory) {
    this.transactionFactory = transactionFactory;
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

  public void getByNumber(String number) {
    // TODO
  }
}
