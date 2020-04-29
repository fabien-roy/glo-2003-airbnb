package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractDeserializer;
import ca.ulaval.glo2003.transactions.exceptions.InvalidServiceFeeException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

// TODO : Test ServiceFeeDeserializer
// TODO : Make PriceDeserializer and ServiceFeeDeserializer use an abstract DoubleDeserializer class
public class ServiceFeeDeserializer
    extends AbstractDeserializer<Double, InvalidServiceFeeException> {

  protected ServiceFeeDeserializer() {
    super(Double.class);
  }

  @Override
  public Class<?> getType() {
    return Double.class;
  }

  @Override
  public void throwException() throws InvalidServiceFeeException {
    throw new InvalidServiceFeeException();
  }

  @Override
  public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws InvalidServiceFeeException {
    double price = 0d;

    try {
      if (jsonParser.isNaN()) throwException();

      price = jsonParser.getDoubleValue();
    } catch (Exception e) {
      throwException();
    }

    return price;
  }
}
