package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidPackageErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidPackageException.class;
  }

  protected String getError() {
    return "INVALID_PACKAGE";
  }

  protected String getDescription() {
    return "package should be one of bloodthirsty, allYouCanDrink, sweetTooth";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
