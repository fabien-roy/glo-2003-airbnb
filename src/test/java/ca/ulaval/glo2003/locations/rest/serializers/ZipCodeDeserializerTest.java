package ca.ulaval.glo2003.locations.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ZipCodeDeserializerTest {

  private static ZipCodeDeserializer zipCodeDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    zipCodeDeserializer = new ZipCodeDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidZipCodeException() {
    assertThrows(InvalidZipCodeException.class, () -> zipCodeDeserializer.throwException());
  }
}
