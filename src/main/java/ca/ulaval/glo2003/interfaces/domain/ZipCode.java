package ca.ulaval.glo2003.interfaces.domain;

import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;

public class ZipCode {

  private String value;
  private String latitude;
  private String longitude;
  private double DEFAULT_MAX_DISTANCE = 10.0; // in kilometers

  private static double DISTANCE_PER_DEGREE_OF_LATITUDE = 110.574; // in kilometers
  private static double DISTANCE_PER_DEGREE_OF_LONGTITUDE = 111.320; // in kilometers

  // TODO Trouver une moyen d'initialiser la latitude et la longitude a l'aide de Zippopotamus
  public ZipCode(String value) {
    this.value = value;
  }
  // TODO le zipCode doit-il être inalterable apres sa creation?
  public ZipCode(String value, String latitude, String longitude) {
    this.value = value;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getValue() {
    return value;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  // TODO
  /**
   * Le comportement actuel de la recherche de lits est inchangé. Le point d’origine doit être un
   * vrai code postal des États-Unis. Tous les lits se trouvant dans le rayon de recherche doivent
   * être retournés, qu’ils soient réservés ou non. Les lits se trouvant au point d’origine doivent
   * aussi être inclus dans le résultat de la recherche.
   */
  public Boolean isWithinRadius(ZipCode origin, double maxDistance) {
    if (maxDistance < 1.0) {
      throw new InvalidMaxDistanceException();
    } else if (origin == null && maxDistance != 0.0) {
      throw new MaxDistanceWithoutOriginException();
    } else {
      boolean result = false;
      double distanceBetweenZipCodes =
              calculateDistanceBetweenCoordinates(
                      this.getLatitude(), this.getLongitude(), origin.getLatitude(), origin.getLongitude());
      if (distanceBetweenZipCodes <= maxDistance) {
        result = true;
      }
      return result;
    }
  }

  public Boolean isWithinRadius(ZipCode origin) {
    return isWithinRadius(origin, DEFAULT_MAX_DISTANCE);
  }

  public static double calculateDistanceBetweenCoordinates(
          String latitude1, String longitude1, String latitude2, String longitude2) {
    double latitude_1 = Double.valueOf(latitude1) * DISTANCE_PER_DEGREE_OF_LATITUDE;
    double flattened_longitude_1 =
            Double.valueOf(longitude1)
                    * DISTANCE_PER_DEGREE_OF_LONGTITUDE
                    * Math.cos(Math.toRadians(Double.valueOf(latitude1)));

    double latitude_2 = Double.valueOf(latitude2) * DISTANCE_PER_DEGREE_OF_LATITUDE;
    double flattened_longitude_2 =
            Double.valueOf(longitude2)
                    * DISTANCE_PER_DEGREE_OF_LONGTITUDE
                    * Math.cos(Math.toRadians(Double.valueOf(latitude2)));

    return Math.sqrt(
            Math.pow(latitude_1 - latitude_2, 2)
                    + Math.pow(flattened_longitude_1 - flattened_longitude_2, 2));
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    ZipCode zipCode = (ZipCode) object;

    return value.equals(zipCode.getValue());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value + ", latitude='" + latitude + ", longitude='" + longitude;
  }
}