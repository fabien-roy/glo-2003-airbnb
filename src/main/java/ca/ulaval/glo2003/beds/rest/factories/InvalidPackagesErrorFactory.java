package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidPackagesErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidPackagesException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_PACKAGES", "package should be one of bloodthirsty, allYouCanDrink, sweetTooth");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
