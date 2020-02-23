package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

public class ArrivalDateInThePastException extends RuntimeException {

  public ArrivalDateInThePastException() {
    super("CANNOT_USE_A_DATE_IN_THE_PAST_FOR_A_BOOKING");
  }
}
