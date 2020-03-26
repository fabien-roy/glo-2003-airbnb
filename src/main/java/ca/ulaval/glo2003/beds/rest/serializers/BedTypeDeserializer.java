package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.parsers.rest.serializers.StringDeserializer;

public class BedTypeDeserializer extends StringDeserializer<InvalidBedTypeException> {

  @Override
  public void throwException() throws InvalidBedTypeException {
    throw new InvalidBedTypeException();
  }
}
