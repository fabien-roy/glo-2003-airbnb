package ca.ulaval.glo2003.locations.rest;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.locations.exceptions.LocationException;
import ca.ulaval.glo2003.locations.rest.factories.InvalidZipCodeErrorFactory;
import ca.ulaval.glo2003.locations.rest.factories.NonExistingZipCodeErrorFactory;
import ca.ulaval.glo2003.locations.rest.factories.UnreachableZippopotamusServerErrorFactory;
import ca.ulaval.glo2003.locations.rest.handlers.LocationExceptionHandler;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class LocationModule extends AbstractModule {

  @Override
  protected void configure() {
    configureErrorFactories();

    bind(LocationExceptionHandler.class);
  }

  private void configureErrorFactories() {
    Multibinder<ErrorFactory<LocationException>> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<ErrorFactory<LocationException>>() {});
    multibinder.addBinding().to(InvalidZipCodeErrorFactory.class);
    multibinder.addBinding().to(NonExistingZipCodeErrorFactory.class);
    multibinder.addBinding().to(UnreachableZippopotamusServerErrorFactory.class);
  }
}
