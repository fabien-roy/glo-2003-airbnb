package ca.ulaval.glo2003.errors;

import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.errors.rest.factories.*;
import ca.ulaval.glo2003.errors.rest.handlers.CatchallExceptionHandler;
import ca.ulaval.glo2003.errors.rest.mappers.ErrorMapper;
import ca.ulaval.glo2003.locations.rest.handlers.LocationExceptionHandler;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class ErrorModule extends AbstractModule {

  @Override
  protected void configure() {
    configureErrorFactories();

    bind(CatchallExceptionHandler.class);
    bind(LocationExceptionHandler.class);
    bind(BedExceptionHandler.class);
    bind(BookingExceptionHandler.class);
    bind(ErrorMapper.class);
  }

  private void configureErrorFactories() {
    Multibinder<CatchallErrorFactory> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<CatchallErrorFactory>() {});
    multibinder.addBinding().to(InvalidFormatErrorFactory.class);
    multibinder.addBinding().to(JsonProcessingErrorFactory.class);
    multibinder.addBinding().to(DefaultErrorFactory.class);
  }
}
