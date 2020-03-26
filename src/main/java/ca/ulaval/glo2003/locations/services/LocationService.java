package ca.ulaval.glo2003.locations.services;

import ca.ulaval.glo2003.locations.converters.ZipCodeConverter;
import ca.ulaval.glo2003.locations.domain.Location;
import ca.ulaval.glo2003.locations.domain.LocationClient;
import ca.ulaval.glo2003.locations.domain.ZipCode;
import com.google.inject.Inject;

public class LocationService {

  private final LocationClient locationClient;
  private final ZipCodeConverter zipCodeConverter;

  @Inject
  public LocationService(LocationClient locationClient, ZipCodeConverter zipCodeConverter) {
    this.locationClient = locationClient;
    this.zipCodeConverter = zipCodeConverter;
  }

  public Location getLocation(String zipCodeValue) {
    ZipCode zipCode = zipCodeConverter.fromString(zipCodeValue);
    return locationClient.getLocation(zipCode);
  }
}
