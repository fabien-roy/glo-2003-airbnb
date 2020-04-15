package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BloodTypesDeserializerTest {

  private static BloodTypesDeserializer capacityDeserializer;
  private static DeserializationContext deserializationContext = mock(DeserializationContext.class);
  private static BeanProperty beanProperty = mock(BeanProperty.class);

  @BeforeAll
  public static void setUpDeserializer() {
    capacityDeserializer = new BloodTypesDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidBloodTypesException() {
    assertThrows(InvalidBloodTypesException.class, () -> capacityDeserializer.throwException());
  }

  @Test
  public void createContextual_shouldReturnBloodTypesDeserializer() {
    JsonDeserializer deserializer =
        capacityDeserializer.createContextual(deserializationContext, beanProperty);

    assertTrue(deserializer instanceof BloodTypesDeserializer);
  }
}
