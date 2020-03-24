package ca.ulaval.glo2003.bookings.exceptions;

public class BookingAlreadyCanceledException extends BookingException {

  // TODO : Add to REST factory when it will be merged
  public BookingAlreadyCanceledException() {
    super("BOOKING_ALREADY_CANCELED");
  }
}
