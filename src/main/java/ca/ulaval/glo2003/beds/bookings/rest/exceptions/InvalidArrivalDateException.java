package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

public class InvalidArrivalDateException extends RuntimeException {

  public InvalidArrivalDateException() {
    super("THE_DATE_IS_NOT_FORMATTED_CORRECTLY");
  }
}
