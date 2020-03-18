package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

public class InvalidNumberOfNights extends BookingException {

  public InvalidNumberOfNights() {
    super("INVALID_NUMBER_OF_NIGHTS");
  }
}
