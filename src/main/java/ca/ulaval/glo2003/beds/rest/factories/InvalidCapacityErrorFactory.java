package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidCapacityErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidCapacityException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_CAPACITY", "minimal capacity should be a positive number");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
