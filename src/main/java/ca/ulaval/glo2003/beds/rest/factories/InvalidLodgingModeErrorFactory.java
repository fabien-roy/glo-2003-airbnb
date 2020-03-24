package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidLodgingModeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidLodgingModeErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidLodgingModeException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_LODGING_MODE", "lodging mode should be one of private or cohabitation");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
