package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import ca.ulaval.glo2003.parsers.rest.PositiveIntegerDeserializer;

public class ColonySizeDeserializer
    extends PositiveIntegerDeserializer<InvalidColonySizeException> {

  @Override
  public void throwException() throws InvalidColonySizeException {
    throw new InvalidColonySizeException();
  }
}
