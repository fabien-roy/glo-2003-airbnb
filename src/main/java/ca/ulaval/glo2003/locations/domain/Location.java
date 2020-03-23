package ca.ulaval.glo2003.locations.domain;

public class Location {

  private String zipCode;
  private double latitude; // TODO : How about Coordinate value object
  private double longitude;

  private static final double DISTANCE_PER_DEGREE_OF_LATITUDE = 110.574; // in kilometers
  private static final double DISTANCE_PER_DEGREE_OF_LONGTITUDE = 111.320; // in kilometers

  public Location(String zipCode, double latitude, double longitude) {
    this.zipCode = zipCode;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getZipCode() {
    return zipCode;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  // TODO
  /**
   * Le comportement actuel de la recherche de lits est inchangé. Le point d’origine doit être un
   * vrai code postal des États-Unis. Tous les lits se trouvant dans le rayon de recherche doivent
   * être retournés, qu’ils soient réservés ou non. Les lits se trouvant au point d’origine doivent
   * aussi être inclus dans le résultat de la recherche.
   */
  public Boolean isWithinRadius(Location origin, double maxDistance) {
    boolean result = false;
    double distanceBetweenZipCodes =
        calculateDistanceBetweenCoordinates(
            this.getLatitude(), this.getLongitude(), origin.getLatitude(), origin.getLongitude());
    if (distanceBetweenZipCodes <= maxDistance) {
      result = true;
    }
    return result;
  }

  public static double calculateDistanceBetweenCoordinates(
      double latitude1, double longitude1, double latitude2, double longitude2) {
    double distanceLatitude1 = latitude1 * DISTANCE_PER_DEGREE_OF_LATITUDE;
    double flattenedLatitude1 =
        longitude1 * DISTANCE_PER_DEGREE_OF_LONGTITUDE * Math.cos(Math.toRadians(latitude1));

    double distanceLatitude2 = latitude2 * DISTANCE_PER_DEGREE_OF_LATITUDE;
    double flattenedLongitude2 =
        longitude2 * DISTANCE_PER_DEGREE_OF_LONGTITUDE * Math.cos(Math.toRadians(latitude2));

    return Math.sqrt(
        Math.pow(distanceLatitude1 - distanceLatitude2, 2)
            + Math.pow(flattenedLatitude1 - flattenedLongitude2, 2));
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Location location = (Location) object;

    return zipCode.equals(location.getZipCode());
  }

  @Override
  public int hashCode() {
    return zipCode.hashCode();
  }
}
