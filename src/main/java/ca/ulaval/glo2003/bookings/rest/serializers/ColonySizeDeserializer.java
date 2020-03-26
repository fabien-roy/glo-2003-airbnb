package ca.ulaval.glo2003.bookings.rest.serializers;

import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import ca.ulaval.glo2003.parsers.rest.serializers.IntegerDeserializer;

public class ColonySizeDeserializer extends IntegerDeserializer<InvalidColonySizeException> {

  @Override
  public void throwException() throws InvalidColonySizeException {
    throw new InvalidColonySizeException();
  }
}
