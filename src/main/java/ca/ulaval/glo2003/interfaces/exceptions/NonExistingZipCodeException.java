package ca.ulaval.glo2003.interfaces.exceptions;

public class NonExistingZipCodeException extends ExternalServiceException {
  public NonExistingZipCodeException() {
    super("NON_EXISTING_ZIP_CODE");
  }
}
