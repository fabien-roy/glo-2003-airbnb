package ca.ulaval.glo2003.bookings;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.mappers.BookingMapper;
import ca.ulaval.glo2003.bookings.rest.BookingParser;
import ca.ulaval.glo2003.bookings.rest.BookingResource;
import ca.ulaval.glo2003.bookings.rest.factories.*;
import ca.ulaval.glo2003.bookings.rest.handlers.BookingExceptionHandler;
import ca.ulaval.glo2003.bookings.rest.serializers.BookingDeserializatingModule;
import ca.ulaval.glo2003.bookings.rest.serializers.ColonySizeDeserializer;
import ca.ulaval.glo2003.bookings.rest.serializers.NumberOfNightsDeserializer;
import ca.ulaval.glo2003.bookings.services.BookingService;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.parsers.domain.serializers.ThrowingDeserializer;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class BookingModule extends AbstractModule {

  @Override
  protected void configure() {
    configureErrorFactories();
    configureSerializers();

    bind(BookingParser.class);
    bind(BookingService.class);
    bind(BookingMapper.class);
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

  private void configureSerializers() {
    Multibinder<ThrowingDeserializer> deserializerMultibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<ThrowingDeserializer>() {});
    deserializerMultibinder.addBinding().to(ColonySizeDeserializer.class);
    deserializerMultibinder.addBinding().to(NumberOfNightsDeserializer.class);
    bind(BookingDeserializatingModule.class);
  }
}
