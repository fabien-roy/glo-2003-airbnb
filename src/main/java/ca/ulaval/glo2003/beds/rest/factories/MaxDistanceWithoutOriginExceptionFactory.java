package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class MaxDistanceWithoutOriginExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
    return exception instanceof MaxDistanceWithoutOriginException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "MAX_DISTANCE_WITHOUT_ORIGIN",
        "an origin point should be provided along with the maximum distance");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
