package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidZipCodeException extends BedException {

  public InvalidZipCodeException() {
    super("INVALID_ZIP_CODE");
  }
}
