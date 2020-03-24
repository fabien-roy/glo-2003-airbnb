package ca.ulaval.glo2003.bookings.exceptions;

public class CancelationNotAllowedException extends BookingException {

  // TODO : Add to REST factory when it will be merged
  public CancelationNotAllowedException() {
    super("CANCELATION_NOT_ALLOWED");
  }
}
