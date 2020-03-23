package ca.ulaval.glo2003.locations.domain.helpers;

import static ca.ulaval.glo2003.beds.domain.helpers.BedObjectMother.*;
import static ca.ulaval.glo2003.locations.domain.helpers.CoordinatesBuilder.someCoordinates;
import static ca.ulaval.glo2003.locations.domain.helpers.LocationObjectMother.*;

import ca.ulaval.glo2003.beds.domain.*;
import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.domain.ZipCode;

public class LocationBuilder {

  private LocationBuilder() {}

  private ZipCode DEFAULT_ZIP_CODE = createZipCode();
  private ZipCode zipCode = DEFAULT_ZIP_CODE;

  private Coordinates DEFAULT_COORDINATES = someCoordinates().build();
  private Coordinates coordinates = DEFAULT_COORDINATES;

  public static LocationBuilder aLocation() {
    return new LocationBuilder();
  }

  public LocationBuilder withZipCode(ZipCode zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public LocationBuilder withCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  public Location build() {
    return new Location(zipCode, coordinates);
  }
}
