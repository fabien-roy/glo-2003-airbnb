package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BedTypeDeserializerTest {

  private static BedTypeDeserializer bedTypeDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    bedTypeDeserializer = new BedTypeDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidCapacityException() {
    assertThrows(InvalidBedTypeException.class, () -> bedTypeDeserializer.throwException());
  }
}
