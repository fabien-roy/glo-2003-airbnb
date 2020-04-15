package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidNumberOfNightsErrorFactory extends BookingErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidNumberOfNightsException.class;
  }

  protected String getError() {
    return "INVALID_NUMBER_OF_NIGHTS";
  }

  protected String getDescription() {
    return "number of nights should be a number between 1 and 90";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
