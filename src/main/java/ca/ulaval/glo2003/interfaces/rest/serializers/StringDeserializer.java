package ca.ulaval.glo2003.interfaces.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public abstract class StringDeserializer<E extends RuntimeException>
    extends AbstractDeserializer<String, E> {

  protected StringDeserializer() {
    super(String.class);
  }

  @Override
  public Class<?> getType() {
    return String.class;
  }

  @Override
  public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    String value = "";

    try {
      value = jsonParser.getText();
    } catch (IOException e) {
      throwException();
    }

    return value;
  }
}
