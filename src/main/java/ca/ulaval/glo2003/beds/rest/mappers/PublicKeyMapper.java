package ca.ulaval.glo2003.beds.rest.mappers;

import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;

public class PublicKeyMapper {

  public static final String PUBLIC_KEY_PATTERN = "([A-Z]|[0-9]){64}";

  public PublicKey fromString(String value) {
    validatePublicKey(value);
    return new PublicKey(value);
  }

  public String toString(PublicKey publicKey) {
    return publicKey.getValue();
  }

  private void validatePublicKey(String publicKey) {
    if (publicKey == null || !publicKey.matches(PUBLIC_KEY_PATTERN))
      throw new InvalidPublicKeyException();
  }
}
