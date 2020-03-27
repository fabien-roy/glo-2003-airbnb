package ca.ulaval.glo2003.interfaces.rest.serializers;

import ca.ulaval.glo2003.errors.exceptions.TestingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

class FakeStringListDeserializer extends StringListDeserializer<TestingException> {

  @Override
  public void throwException() throws TestingException {
    throw new TestingException();
  }

  @Override
  public JsonDeserializer<?> createContextual(
      DeserializationContext deserializationContext, BeanProperty beanProperty) {
    return null;
  }
}
