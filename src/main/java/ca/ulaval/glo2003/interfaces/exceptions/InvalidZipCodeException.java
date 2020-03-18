package ca.ulaval.glo2003.interfaces.exceptions;

public class InvalidZipCodeException extends ExternalServiceException {

  public InvalidZipCodeException() {
    super("INVALID_ZIP_CODE");
  }
}
