package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.parsers.exceptions.ColonySizeParsingException;
import ca.ulaval.glo2003.parsers.rest.PositiveIntegerDeserializer;

public class ColonySizeDeserializer
    extends PositiveIntegerDeserializer<ColonySizeParsingException> {

  @Override
  public void throwException() throws ColonySizeParsingException {
    throw new ColonySizeParsingException();
  }
}
