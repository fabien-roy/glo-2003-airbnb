package ca.ulaval.glo2003.locations.rest.serializers;

import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.parsers.rest.serializers.StringDeserializer;

public class ZipCodeDeserializer extends StringDeserializer<InvalidZipCodeException> {

  @Override
  public void throwException() throws InvalidZipCodeException {
    throw new InvalidZipCodeException();
  }
}
