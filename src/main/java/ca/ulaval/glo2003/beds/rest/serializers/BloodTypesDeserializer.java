package ca.ulaval.glo2003.beds.rest.serializers;

import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import ca.ulaval.glo2003.interfaces.rest.serializers.StringListDeserializer;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BloodTypesDeserializer extends StringListDeserializer<InvalidBloodTypesException> {

  protected BloodTypesDeserializer() {
    super();
  }

  private BloodTypesDeserializer(BeanProperty property) {
    super(property);
  }

  @Override
  public void throwException() throws InvalidBloodTypesException {
    throw new InvalidBloodTypesException();
  }

  @Override
  public JsonDeserializer<?> createContextual(
      DeserializationContext deserializationContext, BeanProperty property) {
    return new BloodTypesDeserializer(property);
  }
}
