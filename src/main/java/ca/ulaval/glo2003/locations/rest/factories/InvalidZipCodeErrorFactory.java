package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.locations.exceptions.LocationServiceException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidZipCodeErrorFactory extends LocationErrorFactory {

  @Override
  public boolean canHandle(LocationServiceException exception) {
    return exception instanceof InvalidZipCodeException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("INVALID_ZIP_CODE", "zip code should be a 5 digits number");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
