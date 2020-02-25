package ca.ulaval.glo2003.beds.domain;

// TODO : Remove
public class Package {

  private PackageNames name;
  private Price pricePerNight;

  public Package(PackageNames name, Price pricePerNight) {
    this.name = name;
    this.pricePerNight = pricePerNight;
  }

  public PackageNames getName() {
    return name;
  }

  public Price getPricePerNight() {
    return pricePerNight;
  }
}
