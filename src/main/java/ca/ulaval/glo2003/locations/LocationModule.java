package ca.ulaval.glo2003.locations;

import ca.ulaval.glo2003.locations.infrastructure.ZippopotamusClient;
import ca.ulaval.glo2003.locations.mappers.LocationMapper;
import ca.ulaval.glo2003.locations.services.LocationClient;
import ca.ulaval.glo2003.locations.services.LocationService;
import ca.ulaval.glo2003.locations.services.OutdatedLocationService;
import com.google.inject.AbstractModule;

public class LocationModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(LocationMapper.class);
    bind(LocationClient.class).to(ZippopotamusClient.class);
    bind(LocationService.class);
    bind(OutdatedLocationService.class); // TODO : Remove
  }
}
