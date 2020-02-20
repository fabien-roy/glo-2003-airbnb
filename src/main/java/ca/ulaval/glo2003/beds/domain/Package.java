package ca.ulaval.glo2003.beds.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Package {

  private PackageNames name;
  private BigDecimal pricePerNight;

  public Package(PackageNames name, BigDecimal pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Package aPackage = (Package) o;
    return name == aPackage.name && pricePerNight.equals(aPackage.pricePerNight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, pricePerNight);
  }
}
