package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import ca.ulaval.glo2003.parsers.rest.PositiveIntegerDeserializer;

public class CapacityDeserializer extends PositiveIntegerDeserializer<InvalidCapacityException> {

  @Override
  public void throwException() throws InvalidCapacityException {
    throw new InvalidCapacityException();
  }
}
