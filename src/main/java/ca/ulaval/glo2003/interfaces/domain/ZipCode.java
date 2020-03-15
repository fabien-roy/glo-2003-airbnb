package ca.ulaval.glo2003.interfaces.domain;


public class ZipCode {

  private String value;

  public ZipCode(String value) {
    this.value = value;
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
}
