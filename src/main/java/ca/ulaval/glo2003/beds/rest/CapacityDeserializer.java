package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.parsers.exceptions.CapacityProcessingException;
import ca.ulaval.glo2003.parsers.rest.PositiveIntegerDeserializer;

public class CapacityDeserializer extends PositiveIntegerDeserializer<CapacityProcessingException> {

  @Override
  public void throwException() throws CapacityProcessingException {
    throw new CapacityProcessingException();
  }
}
