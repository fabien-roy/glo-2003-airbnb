package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.interfaces.rest.serializers.IntegerDeserializer;

public class CapacityDeserializer extends IntegerDeserializer<InvalidCapacityException> {

  @Override
  public void throwException() throws InvalidCapacityException {
    throw new InvalidCapacityException();
  }
}
