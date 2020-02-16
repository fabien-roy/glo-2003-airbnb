package ca.ulaval.glo2003.beds.rest;

import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class BedResource implements RouteGroup {

  public static final String BED_PATH = "/beds";

  @Override
  public void addRoutes() {
    post("", this::add, new ObjectMapper()::writeValueAsString);
    get("", this::getAll, new ObjectMapper()::writeValueAsString);
    get("/:number", this::getByNumber, new ObjectMapper()::writeValueAsString);
  }

  public Object add(Request request, Response response) {
    try {
      BedRequest bedRequest = new ObjectMapper().readValue(request.body(), BedRequest.class);

      response.status(HttpStatus.CREATED_201);
      return bedRequest;
    } catch (JsonProcessingException e) {
      response.status(HttpStatus.BAD_REQUEST_400);
      return "";
    }
  }

  public Object getAll(Request request, Response response) {
    response.status(HttpStatus.OK_200);
    return "Package (param) : " + request.queryParams("package");
  }

  public Object getByNumber(Request request, Response response) {
    response.status(HttpStatus.OK_200);
    return "Bed number : " + request.params("number");
  }
}
