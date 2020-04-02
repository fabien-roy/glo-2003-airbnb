package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.transactions.rest.serializers.PriceDeserializer;

public class PricePerNightDeserializer extends PriceDeserializer<InvalidPackagesException> {

  @Override
  public void throwException() throws InvalidPackagesException {
    throw new InvalidPackagesException();
  }
}
