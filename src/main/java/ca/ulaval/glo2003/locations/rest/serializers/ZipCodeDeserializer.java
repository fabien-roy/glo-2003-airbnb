package ca.ulaval.glo2003.locations.rest.serializers;

import ca.ulaval.glo2003.interfaces.rest.serializers.StringDeserializer;
import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;

public class ZipCodeDeserializer extends StringDeserializer<InvalidZipCodeException> {

  @Override
  public void throwException() throws InvalidZipCodeException {
    throw new InvalidZipCodeException();
  }
}
