package ca.ulaval.glo2003.interfaces.rest.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO : Needs testing
public abstract class StringArrayDeserializer<E extends RuntimeException>
    extends CollectionDeserializer<String[], E> {

  List<String> strings = new ArrayList<>();

  protected StringArrayDeserializer() {
    super();
  }

  protected StringArrayDeserializer(BeanProperty property) {
    super(property);
  }

  @Override
  protected void resetCollection() {
    strings = new ArrayList<>();
  }

  @Override
  protected void addElement(JsonParser jsonParser) throws IOException {
    strings.add(jsonParser.getValueAsString());
  }

  @Override
  protected String[] buildDeserializedCollection() {
    return strings.toArray(new String[0]);
  }
}
