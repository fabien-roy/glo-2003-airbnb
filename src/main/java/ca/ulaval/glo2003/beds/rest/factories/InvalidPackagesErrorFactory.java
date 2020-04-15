package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidPackagesErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidPackagesException.class;
  }

  protected String getError() {
    return "INVALID_PACKAGES";
  }

  protected String getDescription() {
    return "packages should be an array of package name with its corresponding price per night";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
