package ca.ulaval.glo2003.parsers.rest.serializers;

import ca.ulaval.glo2003.parsers.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class StringArrayDeserializer<E extends RuntimeException>
    extends AbstractDeserializer<String[], E> {

  protected StringArrayDeserializer() {
    super(String[].class);
  }

  @Override
  public Class<?> getType() {
    return String[].class;
  }

  @Override
  public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
      List<String> strings = new ArrayList<>();

      try {
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
          strings.add(jsonParser.getValueAsString());
        }
      } catch (IOException e) {
        throwException();
        return new String[0]; // TODO : Return nothing
      }

      return strings.toArray(new String[0]);
    }

    throwException();
    return new String[0]; // TODO : Return nothing
  }
}
