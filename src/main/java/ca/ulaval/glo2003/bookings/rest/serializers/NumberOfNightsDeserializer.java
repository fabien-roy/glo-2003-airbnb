package ca.ulaval.glo2003.bookings.rest.serializers;

import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import ca.ulaval.glo2003.parsers.rest.serializers.IntegerDeserializer;

public class NumberOfNightsDeserializer
    extends IntegerDeserializer<InvalidNumberOfNightsException> {

  @Override
  public void throwException() throws InvalidNumberOfNightsException {
    throw new InvalidNumberOfNightsException();
  }
}
