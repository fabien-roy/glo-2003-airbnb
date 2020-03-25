package ca.ulaval.glo2003.parsers.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public abstract class PositiveIntegerDeserializer<E extends RuntimeException>
    extends AbstractDeserializer<Integer, E> {

  protected PositiveIntegerDeserializer() {
    super(Integer.class);
  }

  @Override
  public Class<?> getType() {
    return Integer.class;
  }

  @Override
  public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    int integer;

    try {
      integer = jsonParser.getIntValue();
    } catch (IOException e) {
      throwException();
      return 0; // TODO : Do not return anything
    }

    if (integer <= 0) throwException();

    return integer;
  }
}
