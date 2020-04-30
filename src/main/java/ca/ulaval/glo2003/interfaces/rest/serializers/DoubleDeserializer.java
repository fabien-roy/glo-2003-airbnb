package ca.ulaval.glo2003.interfaces.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public abstract class DoubleDeserializer<E extends RuntimeException>
    extends AbstractDeserializer<Double, E> {

  protected DoubleDeserializer() {
    super(Double.class);
  }

  @Override
  public Class<?> getType() {
    return Double.class;
  }

  @Override
  public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    double value = 0d;

    try {
      if (jsonParser.isNaN()) throwException();

      value = jsonParser.getDoubleValue();
    } catch (IOException e) {
      throwException();
    }

    return value;
  }
}
