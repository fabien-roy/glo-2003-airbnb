package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.RootResource.ROOT_PATH;
import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static spark.Spark.path;

import ca.ulaval.glo2003.beds.domain.BedRepository;
import ca.ulaval.glo2003.beds.infrastructure.InMemoryBedRepository;
import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.services.BedService;

public class Router {

  // TODO : This is not correct dependency injection
  public static final BedRepository bedRepository = new InMemoryBedRepository();
  public static final BedService bedService = new BedService(bedRepository);

  public static void setUpRoutes() {
    path(ROOT_PATH, new RootResource());
    path(BED_PATH, new BedResource(bedService));
  }
}
