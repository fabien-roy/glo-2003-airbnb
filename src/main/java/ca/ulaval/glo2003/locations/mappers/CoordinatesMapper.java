package ca.ulaval.glo2003.locations.mappers;

import ca.ulaval.glo2003.locations.domain.Coordinates;
import ca.ulaval.glo2003.locations.domain.Latitude;
import ca.ulaval.glo2003.locations.domain.Longitude;
import ca.ulaval.glo2003.locations.infrastructure.PlaceResponse;

public class CoordinatesMapper {

  public Coordinates fromResponse(PlaceResponse place) {
    double latitude = parseDouble(place.getLatitude());
    double longitude = parseDouble(place.getLongitude());

    return new Coordinates(new Latitude(latitude), new Longitude(longitude));
  }

  private double parseDouble(String value) {
    return Double.parseDouble(value.replace(",", "."));
  }
}
