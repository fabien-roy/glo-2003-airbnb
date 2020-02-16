package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.RootResource.ROOT_PATH;
import static ca.ulaval.glo2003.beds.rest.BedResource.BED_PATH;
import static spark.Spark.path;

import ca.ulaval.glo2003.beds.rest.BedResource;
import ca.ulaval.glo2003.beds.services.BedService;

public class Router {

  public static final BedService bedService = new BedService();

  public static void setUpRoutes() {
    path(ROOT_PATH, new RootResource());
    path(BED_PATH, new BedResource(bedService));
  }
}
