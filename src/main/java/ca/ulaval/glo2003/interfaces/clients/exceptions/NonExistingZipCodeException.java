package ca.ulaval.glo2003.interfaces.clients.exceptions;

public class NonExistingZipCodeException extends RuntimeException {
  public NonExistingZipCodeException() {
    super("NON_EXISTING_ZIP_CODE");
  }
}
