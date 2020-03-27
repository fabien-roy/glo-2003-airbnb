package ca.ulaval.glo2003.interfaces.rest.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO : Needs testing
public abstract class StringListDeserializer<E extends RuntimeException>
    extends ListDeserializer<String, E> {

  List<String> strings = new ArrayList<>();

  protected StringListDeserializer() {
    super();
  }

  protected StringListDeserializer(BeanProperty property) {
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
  protected List<String> buildDeserializedCollection() {
    return strings;
  }
}
