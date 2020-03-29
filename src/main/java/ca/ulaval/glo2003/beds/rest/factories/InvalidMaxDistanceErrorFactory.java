package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidMaxDistanceErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidMaxDistanceException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_MAX_DISTANCE", "distance should be a number greater than 0");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
