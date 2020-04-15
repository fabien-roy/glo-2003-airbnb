package ca.ulaval.glo2003.beds.domain;

import static ca.ulaval.glo2003.beds.domain.helpers.PublicKeyObjectMother.createPublicKey;
import static org.junit.jupiter.api.Assertions.*;

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
    otherPublicKey = new PublicKey(publicKey.toString());

    boolean result = publicKey.equals(otherPublicKey);

    assertTrue(result);
  }

  @Test
  public void hashCode_shouldReturnValueHashCode() {
    int hashCode = publicKey.hashCode();

    assertEquals(publicKey.toString().hashCode(), hashCode);
  }
}
