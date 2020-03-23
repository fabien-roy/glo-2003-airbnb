package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidPackageExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
    return exception instanceof InvalidPackageException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_PACKAGE", "package should be one of bloodthirsty, allYouCanDrink, sweetTooth");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
