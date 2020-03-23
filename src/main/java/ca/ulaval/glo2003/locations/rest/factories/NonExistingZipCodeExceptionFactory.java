package ca.ulaval.glo2003.locations.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.errors.ErrorFactory;
import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;
import org.eclipse.jetty.http.HttpStatus;

public class NonExistingZipCodeExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
    return exception instanceof NonExistingZipCodeException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "NON_EXISTING_ZIP_CODE", "zip code is not an existing US postal code");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
