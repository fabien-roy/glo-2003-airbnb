package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CleaningFrequencyDeserializerTest {

  private static CleaningFrequencyDeserializer cleaningFrequencyDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    cleaningFrequencyDeserializer = new CleaningFrequencyDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidCleaningFrequencyException() {
    assertThrows(
        InvalidCleaningFrequencyException.class,
        () -> cleaningFrequencyDeserializer.throwException());
  }
}
