package ca.ulaval.glo2003;

import static spark.Spark.get;
import static spark.Spark.path;

import spark.RouteGroup;

public class RootResource implements RouteGroup {

  public static final String ROOT_PATH = "/";

  @Override
  public void addRoutes() {
    path(ROOT_PATH, () -> get("/hello", (req, res) -> "Hello World"));
  }
}
