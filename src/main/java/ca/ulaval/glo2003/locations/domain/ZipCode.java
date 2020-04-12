package ca.ulaval.glo2003.locations.domain;

public class ZipCode {

  private String value;

  public ZipCode(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ZipCode zipCode = (ZipCode) object;

    return value.equals(zipCode.toString());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
