package ca.ulaval.glo2003.beds.domain;

public class PublicKey {

  private String value;

  public PublicKey(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    PublicKey publicKey = (PublicKey) object;

    return value.equals(publicKey.toString());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
