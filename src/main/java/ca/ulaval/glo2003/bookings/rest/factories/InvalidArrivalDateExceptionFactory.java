package ca.ulaval.glo2003.bookings.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidArrivalDateExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
    return exception instanceof InvalidArrivalDateException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_ARRIVAL_DATE", "arrival date should be formatted as YYYY-MM-DD");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
