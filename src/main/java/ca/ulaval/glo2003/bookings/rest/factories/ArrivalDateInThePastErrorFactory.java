package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import org.eclipse.jetty.http.HttpStatus;

public class ArrivalDateInThePastErrorFactory extends BookingErrorFactory {

  protected Class<?> getAssociatedException() {
    return ArrivalDateInThePastException.class;
  }

  protected String getError() {
    return "ARRIVAL_DATE_IN_THE_PAST";
  }

  protected String getDescription() {
    return "cannot book a stay in the past";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
