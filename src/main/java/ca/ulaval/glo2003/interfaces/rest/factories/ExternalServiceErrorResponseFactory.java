package ca.ulaval.glo2003.interfaces.rest.factories;

import ca.ulaval.glo2003.interfaces.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.NonExistingZipCodeException;
import ca.ulaval.glo2003.interfaces.exceptions.UnreachableZippopotamusServerException;

public class ExternalServiceErrorResponseFactory extends CatchallErrorResponseFactory {

  @Override
  public String create(Exception exception) {
    if (exception instanceof InvalidZipCodeException) {
      return invalidZipCode();
    } else if (exception instanceof NonExistingZipCodeException) {
      return nonExistingZipCode();
    } else if (exception instanceof UnreachableZippopotamusServerException) {
      return unreachableZippopotamusServer();
    } else {
      return defaultResponse();
    }
  }

  static String invalidZipCode() {
    return tryWriteValueAsString("INVALID_ZIP_CODE", "zip code should be a 5 digits number");
  }

  static String nonExistingZipCode() {
    return tryWriteValueAsString(
        "NON_EXISTING_ZIP_CODE", "zip code is not an existing US postal code");
  }

  static String unreachableZippopotamusServer() {
    return tryWriteValueAsString(
        "UNREACHABLE_ZIPPOPOTAMUS_SERVER", "zippopotamus server unreachable");
  }
}