package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.rest.serializers.IntegerDeserializer;
import ca.ulaval.glo2003.transactions.exceptions.InvalidServiceFeeException;

public class ServiceFeeDeserializer extends IntegerDeserializer<InvalidServiceFeeException> {

  @Override
  public void throwException() throws InvalidServiceFeeException {
    throw new InvalidServiceFeeException();
  }
}
