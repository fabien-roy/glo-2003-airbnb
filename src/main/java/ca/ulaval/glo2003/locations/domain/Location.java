package ca.ulaval.glo2003.locations.domain;

public class Location {

  private ZipCode zipCode;
  private Coordinates coordinates;

  public Location(ZipCode zipCode, Coordinates coordinates) {
    this.zipCode = zipCode;
    this.coordinates = coordinates;
  }

  public ZipCode getZipCode() {
    return zipCode;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public boolean isWithinRadius(Location origin, double maxDistance) {
    double distance = coordinates.calculateDistance(origin.getCoordinates());
    return distance <= maxDistance;
  }
}
