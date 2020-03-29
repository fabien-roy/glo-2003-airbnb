package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;
import org.eclipse.jetty.http.HttpStatus;

public class MaxDistanceWithoutOriginErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
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
