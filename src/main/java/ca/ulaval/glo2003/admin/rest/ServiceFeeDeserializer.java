package ca.ulaval.glo2003.admin.rest;

import ca.ulaval.glo2003.interfaces.rest.serializers.DoubleDeserializer;

public class ServiceFeeDeserializer extends DoubleDeserializer<InvalidServiceFeeException> {

  @Override
  public void throwException() throws InvalidServiceFeeException {
    throw new InvalidServiceFeeException();
  }
}
