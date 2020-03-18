package ca.ulaval.glo2003.beds.rest.exceptions;

public class BookingNotAllowedException extends BedException {

  public BookingNotAllowedException() {
    super("BOOKING_NOT_ALLOWED");
  }
}
