package ca.ulaval.glo2003.locations.rest;

import ca.ulaval.glo2003.locations.rest.factories.InvalidZipCodeErrorFactoryService;
import ca.ulaval.glo2003.locations.rest.factories.LocationServiceErrorFactory;
import ca.ulaval.glo2003.locations.rest.factories.NonExistingZipCodeErrorFactory;
import ca.ulaval.glo2003.locations.rest.factories.UnreachableZippopotamusServerErrorFactory;
import ca.ulaval.glo2003.locations.rest.handlers.LocationServiceExceptionHandler;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class LocationServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    configureErrorFactories();

    bind(LocationServiceExceptionHandler.class);
  }

  private void configureErrorFactories() {
    Multibinder<LocationServiceErrorFactory> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<LocationServiceErrorFactory>() {});
    multibinder.addBinding().to(InvalidZipCodeErrorFactoryService.class);
    multibinder.addBinding().to(NonExistingZipCodeErrorFactory.class);
    multibinder.addBinding().to(UnreachableZippopotamusServerErrorFactory.class);
  }
}
