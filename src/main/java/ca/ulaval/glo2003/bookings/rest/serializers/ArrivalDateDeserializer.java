package ca.ulaval.glo2003.bookings.rest.serializers;

import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.interfaces.rest.serializers.StringDeserializer;

public class ArrivalDateDeserializer extends StringDeserializer<InvalidArrivalDateException> {

  @Override
  public void throwException() throws InvalidArrivalDateException {
    throw new InvalidArrivalDateException();
  }
}
