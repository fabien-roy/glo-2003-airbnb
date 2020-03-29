package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidColonySizeErrorFactory extends BookingErrorFactory {

  @Override
  public boolean canHandle(BookingException exception) {
    return exception instanceof InvalidColonySizeException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("INVALID_COLONY_SIZE", "colony size should be a positive number");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
