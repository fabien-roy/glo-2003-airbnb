package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;
import ca.ulaval.glo2003.parsers.rest.serializers.StringDeserializer;

public class PublicKeyDeserializer extends StringDeserializer<InvalidPublicKeyException> {

  @Override
  public void throwException() throws InvalidPublicKeyException {
    throw new InvalidPublicKeyException();
  }
}
