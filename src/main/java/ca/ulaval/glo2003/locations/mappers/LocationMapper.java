package ca.ulaval.glo2003.locations.mappers;

import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.infrastructure.LocationResponse;
import javax.inject.Inject;

public class LocationMapper {

  private final CoordinatesMapper coordinatesMapper;

  @Inject
  public LocationMapper(CoordinatesMapper coordinatesMapper) {
    this.coordinatesMapper = coordinatesMapper;
  }

  public Location fromResponse(LocationResponse location) {
    Coordinates coordinates = coordinatesMapper.fromResponse(location.getPlaces().get(0));

    return new Location(location.getPostCode(), coordinates);
  }
}
