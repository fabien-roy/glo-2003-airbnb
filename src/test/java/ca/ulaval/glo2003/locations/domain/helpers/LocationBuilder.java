package ca.ulaval.glo2003.locations.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.locations.domain.Location;

public class LocationBuilder {

  private LocationBuilder() {}

  private String DEFAULT_ZIP_CODE = createZipCode();
  private String zipCode = DEFAULT_ZIP_CODE;

  private double DEFAULT_LONGITUDE = createLongitude();
  private double longitude = DEFAULT_LONGITUDE;

  private double DEFAULT_LATITUDE = createLatitude();
  private double latitude = DEFAULT_LATITUDE;

  public static LocationBuilder aLocation() {
    return new LocationBuilder();
  }

  public LocationBuilder withZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public LocationBuilder withLongitude(double longitude) {
    this.longitude = longitude;
    return this;
  }

  public LocationBuilder withLatitude(double latitude) {
    this.latitude = latitude;
    return this;
  }

  public Location build() {
    return new Location(zipCode, longitude, latitude);
  }
}
