package ca.ulaval.glo2003.interfaces.rest.factories;

import ca.ulaval.glo2003.interfaces.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.UnreachableZippopotamusServerException;
import org.eclipse.jetty.http.HttpStatus;

public class ExternalServiceErrorStatusFactory extends CatchallErrorStatusFactory {

  @Override
  public int create(Exception exception) {
    if (exception instanceof InvalidZipCodeException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof NonExistingZipCodeException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof UnreachableZippopotamusServerException) {
      return HttpStatus.SERVICE_UNAVAILABLE_503;
    } else {
      return defaultStatus();
    }
  }
}
