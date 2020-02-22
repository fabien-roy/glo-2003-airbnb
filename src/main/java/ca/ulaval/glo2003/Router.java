package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static ca.ulaval.glo2003.interfaces.rest.ErrorResource.ERROR_PATH;
import static ca.ulaval.glo2003.interfaces.rest.RootResource.ROOT_PATH;
import static spark.Spark.path;

import ca.ulaval.glo2003.beds.domain.BedFactory;
import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.rest.mappers.BedMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedMatcherMapper;
import ca.ulaval.glo2003.beds.rest.mappers.BedNumberMapper;
import ca.ulaval.glo2003.beds.services.BedService;
import ca.ulaval.glo2003.interfaces.rest.ErrorResource;
import ca.ulaval.glo2003.interfaces.rest.RootResource;

public class Router {

  // TODO : This is not correct dependency injection
  private static final BedRepository bedRepository = new InMemoryBedRepository();
  private static final BedFactory bedFactory = new BedFactory();
  private static final BedMapper bedMapper = new BedMapper();
  private static final BedNumberMapper bedNumberMapper = new BedNumberMapper();
  private static final BedMatcherMapper bedMatcherMapper = new BedMatcherMapper();
  private static final BedService bedService =
      new BedService(bedFactory, bedMapper, bedNumberMapper, bedMatcherMapper, bedRepository);

  public static void setUpRoutes() {
    path(ERROR_PATH, new ErrorResource());
    path(ROOT_PATH, new RootResource());
    path(BED_PATH, new BedResource(bedService));
  }
}
