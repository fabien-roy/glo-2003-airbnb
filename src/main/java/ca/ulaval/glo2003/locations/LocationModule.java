package ca.ulaval.glo2003.locations;

import ca.ulaval.glo2003.locations.rest.mappers.LocationMapper;
import ca.ulaval.glo2003.locations.rest.services.LocationService;
import com.google.inject.AbstractModule;

public class LocationModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(LocationService.class);
    bind(LocationMapper.class);
  }
}
