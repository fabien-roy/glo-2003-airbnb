package ca.ulaval.glo2003.locations.services;

import ca.ulaval.glo2003.locations.domain.Location;
import com.google.inject.Inject;

public class LocationService {

  private final LocationClient locationClient;

  @Inject
  public LocationService(LocationClient locationClient) {
    this.locationClient = locationClient;
  }

  public Location getLocation(String zipCode) {
    return locationClient.getLocation(zipCode);
  }
}
