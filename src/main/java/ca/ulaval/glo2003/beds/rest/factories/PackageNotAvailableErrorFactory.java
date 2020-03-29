package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.PackageNotAvailableException;
import org.eclipse.jetty.http.HttpStatus;

public class PackageNotAvailableErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof PackageNotAvailableException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "PACKAGE_NOT_AVAILABLE", "selected package is not available for this bed");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
