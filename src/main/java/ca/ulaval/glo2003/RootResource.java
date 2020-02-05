package ca.ulaval.glo2003;

import static spark.Spark.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class RootResource implements RouteGroup {

  public static final String ROOT_PATH = "/";

  @Override
  public void addRoutes() {
    get(ROOT_PATH + "hello", this::helloWorld, new ObjectMapper()::writeValueAsString);
  }

  public Object helloWorld(Request request, Response response) {
    response.status(HttpStatus.OK_200);
    return "Hello World";
  }
}
