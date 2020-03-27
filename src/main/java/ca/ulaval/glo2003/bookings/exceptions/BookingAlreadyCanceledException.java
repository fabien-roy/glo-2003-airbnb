package ca.ulaval.glo2003.bookings.exceptions;

public class BookingAlreadyCanceledException extends BookingException {

  public BookingAlreadyCanceledException() {
    super("BOOKING_ALREADY_CANCELED");
  }
}
