package ca.ulaval.glo2003.bookings.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ColonySizeDeserializerTest {

  private static ColonySizeDeserializer colonySizeDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    colonySizeDeserializer = new ColonySizeDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidColonySizeException() {
    assertThrows(InvalidColonySizeException.class, () -> colonySizeDeserializer.throwException());
  }
}
