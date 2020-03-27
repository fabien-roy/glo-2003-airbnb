package ca.ulaval.glo2003.interfaces.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.IOException;
import java.util.List;

// TODO : Needs testing
public abstract class ListDeserializer<T, E extends RuntimeException>
    extends AbstractDeserializer<List<T>, E> implements ContextualDeserializer {

  protected ListDeserializer() {
    super(List.class);
  }

  protected ListDeserializer(BeanProperty property) {
    super(property.getType());
  }

  protected abstract void resetCollection();

  protected abstract void addElement(JsonParser jsonParser) throws IOException;

  protected abstract List<T> buildDeserializedCollection();

  @Override
  public Class<?> getType() {
    return List.class;
  }

  @Override
  public List<T> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
      resetCollection();

      try {
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
          addElement(jsonParser);
        }
      } catch (IOException e) {
        throwException();
        return null; // TODO : Return nothing
      }

      return buildDeserializedCollection();
    }

    throwException();
    return null; // TODO : Return nothing
  }
}
