package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidColonySizeErrorFactory extends BookingErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidColonySizeException.class;
  }

  protected String getError() {
    return "INVALID_COLONY_SIZE";
  }

  protected String getDescription() {
    return "colony size should be a positive number";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
