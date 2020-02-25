package ca.ulaval.glo2003.beds.domain;

public class Package {

  private PackageNames name;
  private Price pricePerNight;

  public Package(PackageNames name, Price pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
  }

  public Package(PackageNames name) {
    this.name = name;
  }

  public PackageNames getName() {
    return name;
  }

  public Price getPricePerNight() {
    return pricePerNight;
  }
}
