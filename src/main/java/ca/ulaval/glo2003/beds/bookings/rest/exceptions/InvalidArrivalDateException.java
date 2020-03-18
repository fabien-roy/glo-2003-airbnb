package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

public class InvalidArrivalDateException extends BookingException {

  public InvalidArrivalDateException() {
    super("INVALID_ARRIVAL_DATE");
  }
}
