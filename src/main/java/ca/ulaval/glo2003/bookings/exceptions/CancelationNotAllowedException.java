package ca.ulaval.glo2003.bookings.exceptions;

public class CancelationNotAllowedException extends BookingException {

  public CancelationNotAllowedException() {
    super("CANCELATION_NOT_ALLOWED");
  }
}
