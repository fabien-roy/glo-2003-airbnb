package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidNumberOfNightsErrorFactory extends BookingErrorFactory {

  @Override
  public boolean canHandle(BookingException exception) {
    return exception instanceof InvalidNumberOfNightsException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_NUMBER_OF_NIGHTS", "number of nights should be a number between 1 and 90");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
