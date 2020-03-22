package ca.ulaval.glo2003.errors.rest.factories;

import ca.ulaval.glo2003.errors.ErrorFactory;
import ca.ulaval.glo2003.errors.exceptions.InvalidFormatException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidFormatExceptionFactory implements ErrorFactory {

  @Override
  public Exception canHandle() {
    return new InvalidFormatException();
  }

  @Override
  public String createResponse() {
    return ErrorFactory.tryWriteValueAsString("INVALID_FORMAT", "invalid format");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
