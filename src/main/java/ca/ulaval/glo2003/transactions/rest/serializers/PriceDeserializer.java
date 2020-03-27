package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

// TODO : Should PriceDeserializer throw InvalidPackagesException? (it's the only usage)
public class PriceDeserializer extends AbstractDeserializer<Double, InvalidPackagesException> {

  public PriceDeserializer() {
    super(Double.class);
  }

  @Override
  public Class<?> getType() {
    return Double.class;
  }

  @Override
  public void throwException() throws InvalidPackagesException {
    throw new InvalidPackagesException();
  }

  @Override
  public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws InvalidPackagesException {
    double price;

    try {
      if (jsonParser.isNaN()) throwException();

      if (!hasGoodAmountOfDecimals(jsonParser.getText())) throwException();

      price = jsonParser.getDoubleValue();
    } catch (Exception e) {
      throwException();
      return 0.0;
    }

    return price;
  }

  private boolean hasGoodAmountOfDecimals(String price) {
    return price.substring(price.indexOf('.') + 1).length() == 2;
  }
}
