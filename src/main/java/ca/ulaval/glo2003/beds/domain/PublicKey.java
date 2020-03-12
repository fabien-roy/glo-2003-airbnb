package ca.ulaval.glo2003.beds.domain;

public class PublicKey {

  private String value;

  public PublicKey(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    PublicKey publicKey = (PublicKey) object;

    return value.equals(publicKey.getValue());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
