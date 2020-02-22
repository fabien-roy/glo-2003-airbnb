package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.rest.exceptions.BedNotFoundException;
import java.util.UUID;

public class BedNumberMapper {

  public UUID fromString(String number) {
    try {
      return UUID.fromString(number);
    } catch (IllegalArgumentException exception) {
      throw new BedNotFoundException(number);
    }
  }
}
