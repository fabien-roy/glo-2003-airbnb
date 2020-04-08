package ca.ulaval.glo2003.beds.domain;

import java.util.UUID;

public class BedNumber {

  private UUID value;

  public BedNumber(UUID value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    BedNumber bedNumber = (BedNumber) object;

    return value.toString().equals(bedNumber.toString());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
