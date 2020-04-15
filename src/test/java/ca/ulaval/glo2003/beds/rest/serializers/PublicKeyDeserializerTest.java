package ca.ulaval.glo2003.beds.rest.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PublicKeyDeserializerTest {

  private static PublicKeyDeserializer publicKeyDeserializer;

  @BeforeAll
  public static void setUpDeserializer() {
    publicKeyDeserializer = new PublicKeyDeserializer();
  }

  @Test
  public void throwException_shouldThrowInvalidPublicKeyException() {
    assertThrows(InvalidPublicKeyException.class, () -> publicKeyDeserializer.throwException());
  }
}
