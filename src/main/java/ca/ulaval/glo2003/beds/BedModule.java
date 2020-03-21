package ca.ulaval.glo2003.beds;

import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.PackageMapper;
import ca.ulaval.glo2003.beds.services.BedService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class BedModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(BedRepository.class).to(InMemoryBedRepository.class).in(Singleton.class);
    bind(BedService.class);
    bind(BedMapper.class);
    bind(BedMatcherMapper.class);
    bind(PackageMapper.class);
    bind(BedResource.class);
  }
}
