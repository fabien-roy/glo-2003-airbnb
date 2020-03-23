package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class SweetToothDependencyExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
    return exception instanceof SweetToothDependencyException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "CANT_OFFER_SWEET_TOOTH_PACKAGE",
        "in order to offer sweetTooth package, you must also offer the bloodthirsty and allYouCanDrink packages");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
