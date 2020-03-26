package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import ca.ulaval.glo2003.interfaces.rest.serializers.StringArrayDeserializer;

public class BloodTypesDeserializer extends StringArrayDeserializer<InvalidBloodTypesException> {

  @Override
  public void throwException() throws InvalidBloodTypesException {
    throw new InvalidBloodTypesException();
  }
}
