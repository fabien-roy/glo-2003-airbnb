package ca.ulaval.glo2003.beds.rest.mappers;

import static ca.ulaval.glo2003.beds.rest.helpers.BedRequestObjectMother.createOwnerPublicKey;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublicKeyMapperTest {

  private PublicKeyMapper publicKeyMapper;

  @BeforeEach
  public void setUpMapper() {
    publicKeyMapper = new PublicKeyMapper();
  }

  @Test
  public void fromString_shouldMapPublicKey() {
    PublicKey expectedPublicKey = new PublicKey(createOwnerPublicKey());

    PublicKey publicKey = publicKeyMapper.fromString(expectedPublicKey.getValue());

    assertEquals(expectedPublicKey, publicKey);
  }

  @Test
  public void fromString_withoutPublicKey_shouldThrowInvalidPublicKeyException() {
    String invalidPublicKey = null;

    assertThrows(
        InvalidPublicKeyException.class, () -> publicKeyMapper.fromString(invalidPublicKey));
  }

  @Test
  public void fromString_withInvalidPublicKey_shouldThrowInvalidPublicKeyException() {
    String invalidPublicKey = "invalidPublicKey";

    assertThrows(
        InvalidPublicKeyException.class, () -> publicKeyMapper.fromString(invalidPublicKey));
  }

  @Test
  public void toString_shouldMapPublicKey() {
    PublicKey publicKey = new PublicKey(createOwnerPublicKey());
    String expectedValue = publicKey.getValue();

    String value = publicKeyMapper.toString(publicKey);

    assertEquals(expectedValue, value);
  }
}
