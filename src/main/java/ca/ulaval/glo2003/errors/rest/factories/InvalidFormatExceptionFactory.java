package ca.ulaval.glo2003.errors.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.errors.ErrorFactory;
import ca.ulaval.glo2003.errors.exceptions.InvalidFormatException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidFormatExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
    return exception instanceof InvalidFormatException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("INVALID_FORMAT", "invalid format");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
