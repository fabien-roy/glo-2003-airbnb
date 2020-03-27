package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.exceptions.CancelationNotAllowedException;
import org.eclipse.jetty.http.HttpStatus;

public class CancelationNotAllowedErrorFactory extends BookingErrorFactory {

  @Override
  public boolean canHandle(BookingException exception) {
    return exception instanceof CancelationNotAllowedException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "CANCELATION_NOT_ALLOWED", "cancelation period for this booking is over");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
