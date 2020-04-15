package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidArrivalDateErrorFactory extends BookingErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidArrivalDateException.class;
  }

  protected String getError() {
    return "INVALID_ARRIVAL_DATE";
  }

  protected String getDescription() {
    return "arrival date should be formatted as YYYY-MM-DD";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
