package ca.ulaval.glo2003.beds;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedQueryFactory;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.assemblers.*;
import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQueryBuilder;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.mappers.BedMapper;
import ca.ulaval.glo2003.beds.mappers.PackageMapper;
import ca.ulaval.glo2003.beds.rest.*;
import ca.ulaval.glo2003.beds.rest.factories.*;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.bookings.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.parsers.rest.DeserializingModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import java.util.Arrays;
import java.util.Collections;

public class BedModule extends AbstractModule {

  @Override
  protected void configure() {
    configureQueryParamAssemblers();
    configureErrorFactories();
    configureDeserializers();

    bind(BedRepository.class).to(InMemoryBedRepository.class).in(Singleton.class);
    bind(BedQueryBuilder.class).to(InMemoryBedQueryBuilder.class);
    bind(BedQueryFactory.class);
    bind(BedService.class);
    bind(BedMapper.class);
    bind(PackageMapper.class);
    bind(BedResource.class);
    bind(BedExceptionHandler.class);
  }

  private void configureQueryParamAssemblers() {
    Multibinder<BedQueryParamAssembler> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<BedQueryParamAssembler>() {});
    multibinder.addBinding().to(BedTypeQueryParamAssembler.class);
    multibinder.addBinding().to(CleaningFrequencyQueryParamAssembler.class);
    multibinder.addBinding().to(PackageQueryParamAssembler.class);
    multibinder.addBinding().to(BloodTypesQueryParamAssembler.class);
    multibinder.addBinding().to(MinimalCapacityQueryParamAssembler.class);
    multibinder.addBinding().to(ArrivalDateQueryParamAssembler.class);
    multibinder.addBinding().to(NumberOfNightsQueryParamAssembler.class);
    multibinder.addBinding().to(LodgingModeQueryParamAssembler.class);
    multibinder.addBinding().to(OriginQueryParamAssembler.class);
    multibinder.addBinding().to(MaximumDistanceQueryParamAssembler.class);
  }

  private void configureErrorFactories() {
    Multibinder<ErrorFactory<BedException>> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<ErrorFactory<BedException>>() {});
    multibinder.addBinding().to(CantOfferAllYouCanDrinkPackageErrorFactory.class);
    multibinder.addBinding().to(BedAlreadyBookedErrorFactory.class);
    multibinder.addBinding().to(BedNotFoundErrorFactory.class);
    multibinder.addBinding().to(BookingNotAllowedErrorFactory.class);
    multibinder.addBinding().to(ExceedingAccommodationCapacityErrorFactory.class);
    multibinder.addBinding().to(InvalidBedTypeErrorFactory.class);
    multibinder.addBinding().to(InvalidBloodTypesErrorFactory.class);
    multibinder.addBinding().to(InvalidCapacityErrorFactory.class);
    multibinder.addBinding().to(InvalidCleaningFrequencyErrorFactory.class);
    multibinder.addBinding().to(InvalidLodgingModeErrorFactory.class);
    multibinder.addBinding().to(InvalidMaxDistanceErrorFactory.class);
    multibinder.addBinding().to(InvalidPackagesErrorFactory.class);
    multibinder.addBinding().to(InvalidPublicKeyErrorFactory.class);
    multibinder.addBinding().to(MaxDistanceWithoutOriginErrorFactory.class);
    multibinder.addBinding().to(ArrivalDateWithoutMinimalCapacityErrorFactory.class);
    multibinder.addBinding().to(NumberOfNightsWithoutMinimalCapacityErrorFactory.class);
    multibinder.addBinding().to(PackageNotAvailableErrorFactory.class);
    multibinder.addBinding().to(CantOfferSweetToothPackageErrorFactory.class);
  }

  private void configureDeserializers() {
    DeserializingModule deserializingModule =
        new DeserializingModule(
            Arrays.asList(
                new CapacityDeserializer(),
                new BloodTypesDeserializer(),
                new PackagesDeserializer()));
    BedParser bookingParser = new BedParser(Collections.singletonList(deserializingModule));

    bind(BedParser.class).toInstance(bookingParser);
  }
}
