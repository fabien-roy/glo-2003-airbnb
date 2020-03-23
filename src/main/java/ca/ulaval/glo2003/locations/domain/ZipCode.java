package ca.ulaval.glo2003.locations.domain;

public class ZipCode {

  private String value;

  public ZipCode(String zipCode) {
    this.value = zipCode;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ZipCode zipCode = (ZipCode) object;

    return value.equals(zipCode.getValue());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
