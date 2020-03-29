package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PublicKeyTest {

  private static PublicKey publicKey;
  private static PublicKey otherPublicKey;

  @BeforeEach
  public void resetPublicKeys() {
    publicKey = createPublicKey();
    otherPublicKey = createPublicKey();
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotPublicKey() {
    Object object = new Object();

    boolean result = publicKey.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    boolean result = publicKey.equals(otherPublicKey);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    otherPublicKey = new PublicKey(publicKey.getValue());

    boolean result = publicKey.equals(otherPublicKey);

    assertTrue(result);
  }
}