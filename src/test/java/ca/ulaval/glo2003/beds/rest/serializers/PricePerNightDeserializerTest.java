package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PricePerNightDeserializerTest {

  private static PricePerNightDeserializer pricePerNightDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    pricePerNightDeserializer = new PricePerNightDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidPricePerNightException() {
    assertThrows(InvalidPackagesException.class, () -> pricePerNightDeserializer.throwException());
  }
}
