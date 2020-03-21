package ca.ulaval.glo2003.bookings.exceptions;

public abstract class BookingException extends RuntimeException {

  public BookingException(String message) {
    super(message);
  }
}
