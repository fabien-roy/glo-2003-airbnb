package ca.ulaval.glo2003;

import static ca.ulaval.glo2003.RootResource.ROOT_PATH;
import static spark.Spark.path;

public class Router {

  public static void setUpRoutes() {
    path(ROOT_PATH, new RootResource());
  }
}
