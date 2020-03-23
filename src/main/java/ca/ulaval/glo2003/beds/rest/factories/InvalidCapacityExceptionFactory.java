package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidCapacityExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
    return exception instanceof InvalidCapacityException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_CAPACITY", "minimal capacity should be a positive number");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
