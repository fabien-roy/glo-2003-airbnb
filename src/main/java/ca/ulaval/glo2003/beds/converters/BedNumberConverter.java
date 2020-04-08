package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.domain.BedNumber;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.UUID;

public class BedNumberConverter {

  public BedNumber fromString(String number) {
    UUID value;

    try {
      value = UUID.fromString(number);
    } catch (IllegalArgumentException exception) {
      throw new BedNotFoundException(number);
    }

    return new BedNumber(value);
  }
}
