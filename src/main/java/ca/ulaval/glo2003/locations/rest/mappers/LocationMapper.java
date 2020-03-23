package ca.ulaval.glo2003.locations.rest.mappers;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.rest.LocationResponse;

public class LocationMapper {

  public Location fromResponse(LocationResponse response) {

    String zipCode = response.getPostCode();
    String latitude = response.getPlaces().get(0).getLatitude();
    String longitude = response.getPlaces().get(0).getLongitude();

    return new Location(zipCode, latitude, longitude);
  }
}
