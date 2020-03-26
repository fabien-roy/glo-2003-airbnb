package ca.ulaval.glo2003.parsers.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public abstract class IntegerDeserializer<E extends RuntimeException>
    extends AbstractDeserializer<Integer, E> {

  protected IntegerDeserializer() {
    super(Integer.class);
  }

  @Override
  public Class<?> getType() {
    return Integer.class;
  }

  @Override
  public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    int integer = 0;

    try {
      integer = jsonParser.getIntValue();
    } catch (IOException e) {
      throwException();
    }

    return integer;
  }
}
