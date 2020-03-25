package ca.ulaval.glo2003.parsers.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.List;

public abstract class NonEmptyArrayDeserializer<E extends JsonMappingException>
    extends AbstractDeserializer<Object[], E> {

  protected NonEmptyArrayDeserializer() {
    super(Object[].class);
  }

  @Override
  public Class<Object[]> getType() {
    return Object[].class;
  }

  @Override
  public Object[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    List<Object> array;

    try {
      array = jsonParser.readValueAs(List.class);
    } catch (IOException e) {
      throwException();
      return null; // TODO : Do not return anything
    }

    if (array.isEmpty()) throwException();

    return array.toArray();
  }
}
