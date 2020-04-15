package ca.ulaval.glo2003.bookings.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ArrivalDateDeserializerTest {

  private static ArrivalDateDeserializer arrivalDateDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    arrivalDateDeserializer = new ArrivalDateDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidArrivalDateException() {
    assertThrows(InvalidArrivalDateException.class, () -> arrivalDateDeserializer.throwException());
  }
}
