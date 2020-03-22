package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorStatusFactory;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import org.eclipse.jetty.http.HttpStatus;

public class LocationServiceErrorStatusFactory extends CatchallErrorStatusFactory {

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
