package ca.ulaval.glo2003.beds.mappers;

import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestObjectMother.createOwnerPublicKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublicKeyConverterTest {

  private PublicKeyConverter publicKeyConverter;

  @BeforeEach
  public void setUpConverter() {
    publicKeyConverter = new PublicKeyConverter();
  }

  @Test
  public void fromString_shouldConvertPublicKey() {
    PublicKey expectedPublicKey = new PublicKey(createOwnerPublicKey());

    PublicKey publicKey = publicKeyConverter.fromString(expectedPublicKey.getValue());

    assertEquals(expectedPublicKey, publicKey);
  }

  @Test
  public void fromString_withInvalidPublicKey_shouldThrowInvalidPublicKeyException() {
    String invalidPublicKey = "invalidPublicKey";

    assertThrows(
        InvalidPublicKeyException.class, () -> publicKeyConverter.fromString(invalidPublicKey));
  }

  @Test
  public void toString_shouldConvertPublicKey() {
    PublicKey publicKey = new PublicKey(createOwnerPublicKey());
    String expectedValue = publicKey.getValue();

    String value = publicKeyConverter.toString(publicKey);

    assertEquals(expectedValue, value);
  }
}
