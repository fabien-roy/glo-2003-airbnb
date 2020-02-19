package ca.ulaval.glo2003.beds.domain;

import java.math.BigDecimal;

public class Package {

  private PackageNames name;
  private BigDecimal pricePerNight;

  public Package(PackageNames name, BigDecimal pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
  }
}
