package ca.ulaval.glo2003.beds.rest;

import ca.ulaval.glo2003.parsers.exceptions.CapacityParsingException;
import ca.ulaval.glo2003.parsers.rest.PositiveIntegerDeserializer;

public class CapacityDeserializer extends PositiveIntegerDeserializer<CapacityParsingException> {

  @Override
  public void throwException() throws CapacityParsingException {
    throw new CapacityParsingException();
  }
}
