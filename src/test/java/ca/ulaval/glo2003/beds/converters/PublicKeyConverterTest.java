package ca.ulaval.glo2003.beds.converters;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.createOwnerPublicKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublicKeyConverterTest {

  private static PublicKeyConverter publicKeyConverter;

  private static PublicKey publicKey;

  @BeforeAll
  public static void setUpConverter() {
    publicKeyConverter = new PublicKeyConverter();
  }

  @BeforeEach
  public void resetMock() {
    publicKey = createOwnerPublicKey();
  }

  @Test
  public void fromString_shouldConvertPublicKey() {
    PublicKey actualPublicKey = publicKeyConverter.fromString(publicKey.toString());

    assertEquals(publicKey, actualPublicKey);
  }

  @Test
  public void fromString_withLowerCaseString_shouldConvertPublicKey() {
    String lowerCaseString = publicKey.toString().toLowerCase();

    PublicKey actualPublicKey = publicKeyConverter.fromString(lowerCaseString);

    assertEquals(publicKey, actualPublicKey);
  }

  @Test
  public void fromString_withInvalidPublicKey_shouldThrowInvalidPublicKeyException() {
    String invalidPublicKey = "invalidPublicKey";

    assertThrows(
        InvalidPublicKeyException.class, () -> publicKeyConverter.fromString(invalidPublicKey));
  }

  @Test
  public void fromString_withNullPublicKey_shouldThrowInvalidPublicKeyException() {
    String invalidPublicKey = null;

    assertThrows(
        InvalidPublicKeyException.class, () -> publicKeyConverter.fromString(invalidPublicKey));
  }
}
