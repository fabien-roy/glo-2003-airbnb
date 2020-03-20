package ca.ulaval.glo2003.beds.bookings.exceptions;

public class ArrivalDateInThePastException extends BookingException {

  public ArrivalDateInThePastException() {
    super("ARRIVAL_DATE_IN_THE_PAST");
  }
}
