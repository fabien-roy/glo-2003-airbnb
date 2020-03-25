package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.parsers.exceptions.ColonySizeProcessingException;
import ca.ulaval.glo2003.parsers.rest.PositiveIntegerDeserializer;

public class ColonySizeDeserializer
    extends PositiveIntegerDeserializer<ColonySizeProcessingException> {

  @Override
  public void throwException() throws ColonySizeProcessingException {
    throw new ColonySizeProcessingException();
  }
}
