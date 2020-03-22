package ca.ulaval.glo2003.locations.exceptions;

public class InvalidZipCodeException extends LocationServiceException {

  public InvalidZipCodeException() {
    super("INVALID_ZIP_CODE");
  }
}
