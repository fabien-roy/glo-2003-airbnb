package ca.ulaval.glo2003.interfaces.rest.exceptions;

public class InvalidZipCodeException extends RuntimeException {

  public InvalidZipCodeException() {
    super("INVALID_ZIP_CODE");
  }
}
