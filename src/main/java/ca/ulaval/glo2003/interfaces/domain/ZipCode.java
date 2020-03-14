package ca.ulaval.glo2003.interfaces.domain;

import java.util.Comparator;

public class ZipCode implements Comparable<ZipCode> {

  private String value;
  private final Comparator<ZipCode> comparator = Comparator.comparing(ZipCode::getValue);

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

  @Override
  public int compareTo(ZipCode other) {
    return comparator.compare(this, other);
  }
}
