package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.interfaces.rest.serializers.StringDeserializer;

public class PackageNameDeserializer extends StringDeserializer<InvalidPackagesException> {

  @Override
  public void throwException() throws InvalidPackagesException {
    throw new InvalidPackagesException();
  }
}
