package ca.ulaval.glo2003.admin.rest;

import ca.ulaval.glo2003.interfaces.rest.serializers.IntegerDeserializer;

public class ServiceFeeDeserializer extends IntegerDeserializer<InvalidServiceFeeException> {

  @Override
  public void throwException() throws InvalidServiceFeeException {
    throw new InvalidServiceFeeException();
  }
}
