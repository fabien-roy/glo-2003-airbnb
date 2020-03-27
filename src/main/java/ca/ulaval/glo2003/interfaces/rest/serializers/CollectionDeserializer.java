package ca.ulaval.glo2003.interfaces.rest.serializers;

import ca.ulaval.glo2003.interfaces.domain.serializers.AbstractDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.IOException;
import java.util.Collection;

// TODO : Needs testing
public abstract class CollectionDeserializer<T, E extends RuntimeException>
    extends AbstractDeserializer<T, E> implements ContextualDeserializer {

  protected CollectionDeserializer() {
    super(Collection.class);
  }

  protected CollectionDeserializer(BeanProperty property) {
    super(property.getType());
  }

  protected abstract void resetCollection();

  protected abstract void addElement(JsonParser jsonParser) throws IOException;

  protected abstract T buildDeserializedCollection();

  @Override
  public Class<?> getType() {
    return Collection.class;
  }

  @Override
  public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws E {
    if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
      resetCollection();

      try {
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) throwException();

        do {
          addElement(jsonParser);
        } while (jsonParser.nextToken() != JsonToken.END_ARRAY);
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
