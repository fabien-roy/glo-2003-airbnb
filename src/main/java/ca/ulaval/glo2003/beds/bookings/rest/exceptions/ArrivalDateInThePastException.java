package ca.ulaval.glo2003.beds.bookings.rest.exceptions;

public class ArrivalDateInThePastException extends RuntimeException {

  public ArrivalDateInThePastException() {
    super("ARRIVAL_DATE_IN_THE_PAST");
  }
}