package ca.ulaval.glo2003.locations.converters;

import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.infrastructure.LocationResponse;
import javax.inject.Inject;

public class LocationConverter {

  private final CoordinatesConverter coordinatesConverter;

  @Inject
  public LocationConverter(CoordinatesConverter coordinatesConverter) {
    this.coordinatesConverter = coordinatesConverter;
  }

  public Location fromResponse(LocationResponse location) {
    Coordinates coordinates = coordinatesConverter.fromResponse(location.getPlaces().get(0));
    ZipCode zipCode = new ZipCode(location.getPostCode());

    return new Location(zipCode, coordinates);
  }
}
