package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.PackageNotAvailableException;
import org.eclipse.jetty.http.HttpStatus;

public class PackageNotAvailableErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return PackageNotAvailableException.class;
  }

  protected String getError() {
    return "PACKAGE_NOT_AVAILABLE";
  }

  protected String getDescription() {
    return "selected package is not available for this bed";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
