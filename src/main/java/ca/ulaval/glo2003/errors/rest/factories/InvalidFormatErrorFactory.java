package ca.ulaval.glo2003.errors.rest.factories;

import ca.ulaval.glo2003.errors.exceptions.InvalidFormatException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidFormatErrorFactory extends CatchallErrorFactory {

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
