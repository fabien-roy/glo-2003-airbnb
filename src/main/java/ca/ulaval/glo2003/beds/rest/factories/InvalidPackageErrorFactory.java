package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidPackageErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidPackageException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_PACKAGE", "package should be one of bloodthirsty, allYouCanDrink, sweetTooth");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
