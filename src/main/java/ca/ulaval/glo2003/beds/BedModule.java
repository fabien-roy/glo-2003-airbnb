package ca.ulaval.glo2003.beds;

import ca.ulaval.glo2003.beds.converters.BedConverter;
import ca.ulaval.glo2003.beds.converters.PackageConverter;
import ca.ulaval.glo2003.beds.converters.validators.AllYouCanDrinkValidator;
import ca.ulaval.glo2003.beds.converters.validators.BloodthirstyValidator;
import ca.ulaval.glo2003.beds.converters.validators.PackageValidator;
import ca.ulaval.glo2003.beds.converters.validators.SweetToothValidator;
import ca.ulaval.glo2003.beds.domain.BedFactory;
import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedQueryFactory;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.assemblers.*;
import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQueryBuilder;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.rest.BedMapper;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.rest.factories.*;
import ca.ulaval.glo2003.beds.rest.handlers.BedExceptionHandler;
import ca.ulaval.glo2003.beds.rest.serializers.*;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class BedModule extends AbstractModule {

  @Override
  protected void configure() {
    configureQueryParamAssemblers();
    configureErrorFactories();
    configurePackagesValidators();

    bind(BedRepository.class).to(InMemoryBedRepository.class).in(Singleton.class);
    bind(BedQueryBuilder.class).to(InMemoryBedQueryBuilder.class);
    bind(BedQueryFactory.class);
    bind(BedSerializingModule.class);
    bind(BedDeserializingModule.class);
    bind(BedMapper.class);
    bind(BedService.class);
    bind(BedConverter.class);
    bind(PackageConverter.class);
    bind(BedResource.class);
    bind(BedExceptionHandler.class);
    bind(BedFactory.class);
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
    multibinder.addBinding().to(InvalidMinimalCapacityErrorFactory.class);
    multibinder.addBinding().to(InvalidCleaningFrequencyErrorFactory.class);
    multibinder.addBinding().to(InvalidLodgingModeErrorFactory.class);
    multibinder.addBinding().to(InvalidMaxDistanceErrorFactory.class);
    multibinder.addBinding().to(InvalidPackageErrorFactory.class);
    multibinder.addBinding().to(InvalidPackagesErrorFactory.class);
    multibinder.addBinding().to(InvalidPublicKeyErrorFactory.class);
    multibinder.addBinding().to(MaxDistanceWithoutOriginErrorFactory.class);
    multibinder.addBinding().to(ArrivalDateWithoutMinimalCapacityErrorFactory.class);
    multibinder.addBinding().to(NumberOfNightsWithoutMinimalCapacityErrorFactory.class);
    multibinder.addBinding().to(PackageNotAvailableErrorFactory.class);
    multibinder.addBinding().to(CantOfferSweetToothPackageErrorFactory.class);
  }

  private void configurePackagesValidators() {
    Multibinder<PackageValidator> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<PackageValidator>() {});
    multibinder.addBinding().to(AllYouCanDrinkValidator.class);
    multibinder.addBinding().to(SweetToothValidator.class);

    bind(PackageValidator.class).to(BloodthirstyValidator.class);
  }
}
