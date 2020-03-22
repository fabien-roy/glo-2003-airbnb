package ca.ulaval.glo2003.beds;

import ca.ulaval.glo2003.beds.domain.BedQueryBuilder;
import ca.ulaval.glo2003.beds.domain.BedQueryFactory;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.domain.assemblers.*;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedQueryBuilder;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.PackageMapper;
import ca.ulaval.glo2003.beds.services.BedService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class BedModule extends AbstractModule {

  @Override
  protected void configure() {
    configureQueryParamAssemblers();

    bind(BedRepository.class).to(InMemoryBedRepository.class).in(Singleton.class);
    bind(BedQueryBuilder.class).to(InMemoryBedQueryBuilder.class);
    bind(BedQueryFactory.class);
    bind(BedService.class);
    bind(BedMapper.class);
    bind(BedMatcherMapper.class);
    bind(PackageMapper.class);
    bind(BedResource.class);
  }

  private void configureQueryParamAssemblers() {
    Multibinder<BedQueryParamAssembler> multibinder =
        Multibinder.newSetBinder(binder(), new TypeLiteral<BedQueryParamAssembler>() {});
    multibinder.addBinding().to(BedTypeQueryParamAssembler.class);
    multibinder.addBinding().to(CleaningFrequencyQueryParamAssembler.class);
    multibinder.addBinding().to(PackageQueryParamAssembler.class);
    multibinder.addBinding().to(BloodTypesQueryParamAssembler.class);
    multibinder.addBinding().to(MinimalCapacityQueryParamAssembler.class);
  }
}
