package ca.ulaval.glo2003.transactions.rest.serializers;

import ca.ulaval.glo2003.interfaces.rest.serializers.DoubleDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

public abstract class PriceDeserializer<E extends RuntimeException> extends DoubleDeserializer<E> {

  private static final int MAX_DECIMALS = 2;

  @Override
  public Class<?> getType() {
    return Double.class;
  }

  @Override
  public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    Double price = super.deserialize(jsonParser, deserializationContext);

    try {
      if (!hasGoodAmountOfDecimals(jsonParser.getText())) throwException();

      price = jsonParser.getDoubleValue();
    } catch (Exception e) {
      throwException();
    }

    return price;
  }

  private boolean hasGoodAmountOfDecimals(String price) {
    return !price.contains(".") || price.substring(price.indexOf('.') + 1).length() <= MAX_DECIMALS;
  }
}
