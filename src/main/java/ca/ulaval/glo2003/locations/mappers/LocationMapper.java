package ca.ulaval.glo2003.locations.mappers;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.infrastructure.LocationResponse;

public class LocationMapper {

  public Location fromResponse(LocationResponse response) {
    String zipCode = response.getPostCode();
    double latitude = parseDouble(response.getPlaces().get(0).getLatitude());
    double longitude = parseDouble(response.getPlaces().get(0).getLongitude());

    return new Location(zipCode, latitude, longitude);
  }

  private double parseDouble(String value) {
    return Double.parseDouble(value.replace(",", "."));
  }
}
