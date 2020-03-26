package ca.ulaval.glo2003.locations.converters;

import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;

public class ZipCodeConverter {

  public ZipCode fromString(String value) {
    validateLength(value);
    validateNumeric(value);
    return new ZipCode(value);
  }

  private void validateLength(String value) {
    if (value.length() != 5) {
      throw new InvalidZipCodeException();
    }
  }

  private void validateNumeric(String value) {
    try {
      Double.parseDouble(value);
    } catch (NumberFormatException e) {
      throw new InvalidZipCodeException();
    }
  }
}
