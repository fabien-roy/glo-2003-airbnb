package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidMinimalCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidMinimalCapacityErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidMinimalCapacityException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_MINIMAL_CAPACITY", "minimal capacity should be a positive number");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
