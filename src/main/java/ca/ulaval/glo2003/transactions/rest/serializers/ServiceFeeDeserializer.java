package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.rest.serializers.DoubleDeserializer;
import ca.ulaval.glo2003.transactions.exceptions.InvalidServiceFeeException;

public class ServiceFeeDeserializer extends DoubleDeserializer<InvalidServiceFeeException> {

  @Override
  public void throwException() throws InvalidServiceFeeException {
    throw new InvalidServiceFeeException();
  }
}
