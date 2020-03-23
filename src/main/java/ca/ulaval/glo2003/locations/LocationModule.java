package ca.ulaval.glo2003.locations;

import ca.ulaval.glo2003.locations.services.OutdatedLocationService;
import com.google.inject.AbstractModule;

public class LocationModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(OutdatedLocationService.class);
  }
}
