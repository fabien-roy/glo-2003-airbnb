package ca.ulaval.glo2003.locations.domain.helpers;

import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createLatitude;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.createLongitude;

import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Latitude;
import ca.ulaval.glo2003.locations.domain.Longitude;

public class CoordinatesBuilder {

  private CoordinatesBuilder() {}

  private Latitude DEFAULT_LATITUDE = createLatitude();
  private Latitude latitude = DEFAULT_LATITUDE;

  private Longitude DEFAULT_LONGITUDE = createLongitude();
  private Longitude longitude = DEFAULT_LONGITUDE;

  public static CoordinatesBuilder someCoordinates() {
    return new CoordinatesBuilder();
  }

  public CoordinatesBuilder withLatitude(double latitude) {
    this.latitude = new Latitude(latitude);
    return this;
  }

  public CoordinatesBuilder withLongitude(double longitude) {
    this.longitude = new Longitude(longitude);
    return this;
  }

  public Coordinates build() {
    return new Coordinates(latitude, longitude);
  }
}
