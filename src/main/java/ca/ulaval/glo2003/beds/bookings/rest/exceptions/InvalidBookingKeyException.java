package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

public class InvalidBookingKeyException extends RuntimeException {

  public InvalidBookingKeyException() {
    super("THE_KEY_IS_INVALID");
  }
}
