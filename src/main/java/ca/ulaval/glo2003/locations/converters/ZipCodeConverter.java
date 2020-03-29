package ca.ulaval.glo2003.locations.converters;

import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;

public class ZipCodeConverter {

  public ZipCode fromString(String value) {
    validateString(value);
    validateNumeric(value);
    return new ZipCode(value);
  }

  private void validateString(String value) {
    if (value == null || value.length() != 5) {
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
