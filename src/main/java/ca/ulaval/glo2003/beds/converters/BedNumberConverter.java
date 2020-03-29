package ca.ulaval.glo2003.beds.converters;

import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import java.util.UUID;

public class BedNumberConverter {

  public UUID fromString(String number) {
    try {
      return UUID.fromString(number);
    } catch (IllegalArgumentException exception) {
      throw new BedNotFoundException(number);
    }
  }

  public String toString(UUID number) {
    return number.toString();
  }
}
