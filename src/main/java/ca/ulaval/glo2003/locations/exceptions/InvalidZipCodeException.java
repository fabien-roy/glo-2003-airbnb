package ca.ulaval.glo2003.locations.exceptions;

public class InvalidZipCodeException extends LocationException {

  public InvalidZipCodeException() {
    super("INVALID_ZIP_CODE");
  }
}
