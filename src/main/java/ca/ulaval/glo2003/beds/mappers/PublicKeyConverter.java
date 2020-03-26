package ca.ulaval.glo2003.beds.mappers;

import ca.ulaval.glo2003.beds.domain.PublicKey;
import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;

public class PublicKeyConverter {

  public static final String PUBLIC_KEY_PATTERN = "([A-Z]|[0-9]){64}";

  public PublicKey fromString(String value) {
    if (!value.matches(PUBLIC_KEY_PATTERN)) throw new InvalidPublicKeyException();

    return new PublicKey(value);
  }

  public String toString(PublicKey publicKey) {
    return publicKey.getValue();
  }
}
