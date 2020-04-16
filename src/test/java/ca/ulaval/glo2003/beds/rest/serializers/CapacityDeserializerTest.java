package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CapacityDeserializerTest {

  private static CapacityDeserializer capacityDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    capacityDeserializer = new CapacityDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidCapacityException() {
    assertThrows(InvalidCapacityException.class, () -> capacityDeserializer.throwException());
  }
}
