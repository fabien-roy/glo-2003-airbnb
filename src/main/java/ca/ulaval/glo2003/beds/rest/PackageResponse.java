package ca.ulaval.glo2003.beds.rest;

public class PackageResponse {

  private String name;
  private double pricePerNight;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(double pricePerNight) {
    this.pricePerNight = pricePerNight;
  }
}
