package ca.ulaval.glo2003.bookings.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NumberOfNightsDeserializerTest {

  private static NumberOfNightsDeserializer numberOfNightsDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    numberOfNightsDeserializer = new NumberOfNightsDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidNumberOfNightsException() {
    assertThrows(
        InvalidNumberOfNightsException.class, () -> numberOfNightsDeserializer.throwException());
  }
}
