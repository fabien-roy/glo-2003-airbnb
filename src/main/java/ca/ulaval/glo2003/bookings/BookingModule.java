package ca.ulaval.glo2003.bookings;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.rest.BookingMapper;
import ca.ulaval.glo2003.bookings.rest.BookingResource;
import ca.ulaval.glo2003.bookings.rest.factories.*;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.bookings.rest.serializers.BookingDeserializingModule;
import ca.ulaval.glo2003.bookings.services.BookingService;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class BookingModule extends AbstractModule {

  @Override
  protected void configure() {
    configureErrorFactories();

    bind(BookingDeserializingModule.class);
    bind(BookingMapper.class);
    bind(BookingService.class);
    bind(ca.ulaval.glo2003.bookings.mappers.BookingMapper.class);
    bind(BookingResource.class);
    bind(BookingExceptionHandler.class);
  }

  private void configureErrorFactories() {
    Multibinder<ErrorFactory<BookingException>> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<ErrorFactory<BookingException>>() {});
    multibinder.addBinding().to(ArrivalDateInThePastErrorFactory.class);
    multibinder.addBinding().to(BookingNotFoundErrorFactory.class);
    multibinder.addBinding().to(ExceedingResidualCapacityErrorFactory.class);
    multibinder.addBinding().to(InvalidArrivalDateErrorFactory.class);
    multibinder.addBinding().to(InvalidColonySizeErrorFactory.class);
    multibinder.addBinding().to(InvalidNumberOfNightsErrorFactory.class);
  }
}
