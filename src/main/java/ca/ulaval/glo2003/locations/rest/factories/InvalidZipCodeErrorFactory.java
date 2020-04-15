package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidZipCodeErrorFactory extends LocationErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidZipCodeException.class;
  }

  protected String getError() {
    return "INVALID_ZIP_CODE";
  }

  protected String getDescription() {
    return "zip code should be a 5 digits number";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
