package ca.ulaval.glo2003.locations.domain;

public class Coordinates {

  private Latitude latitude;
  private Longitude longitude;

  public Coordinates(Latitude latitude, Longitude longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Latitude getLatitude() {
    return latitude;
  }

  public Longitude getLongitude() {
    return longitude;
  }

  public double calculateDistance(Coordinates other) {
    double flattenedLatitude1 = longitude.toKm() * Math.cos(Math.toRadians(latitude.toDegrees()));
    double flattenedLongitude2 =
        other.getLongitude().toKm() * Math.cos(Math.toRadians(other.getLatitude().toDegrees()));

    return Math.sqrt(
        Math.pow(latitude.toKm() - other.getLatitude().toKm(), 2)
            + Math.pow(flattenedLatitude1 - flattenedLongitude2, 2));
  }
}
