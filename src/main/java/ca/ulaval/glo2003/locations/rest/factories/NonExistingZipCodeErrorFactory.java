package ca.ulaval.glo2003.locations.rest.factories;

import org.eclipse.jetty.http.HttpStatus;

public class NonExistingZipCodeErrorFactory extends LocationErrorFactory {

  protected Class<?> getAssociatedException() {
    return NonExistingZipCodeErrorFactory.class;
  }

  protected String getError() {
    return "NON_EXISTING_ZIP_CODE";
  }

  protected String getDescription() {
    return "zip code is not an existing US postal code";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
