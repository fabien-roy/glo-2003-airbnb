package ca.ulaval.glo2003.locations.services;

import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.domain.LocationClient;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import ca.ulaval.glo2003.locations.mappers.ZipCodeMapper;
import com.google.inject.Inject;

public class LocationService {

  private final LocationClient locationClient;
  private final ZipCodeMapper zipCodeMapper;

  @Inject
  public LocationService(LocationClient locationClient, ZipCodeMapper zipCodeMapper) {
    this.locationClient = locationClient;
    this.zipCodeMapper = zipCodeMapper;
  }

  public Location getLocation(String zipCodeValue) {
    ZipCode zipCode = zipCodeMapper.fromString(zipCodeValue);
    return locationClient.getLocation(zipCode);
  }
}
