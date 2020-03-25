package ca.ulaval.glo2003.locations.exceptions;

public class NonExistingZipCodeException extends LocationException {
  public NonExistingZipCodeException() {
    super("NON_EXISTING_ZIP_CODE");
  }
}
