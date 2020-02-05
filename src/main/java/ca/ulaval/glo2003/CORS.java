package ca.ulaval.glo2003;

import org.eclipse.jetty.http.HttpStatus;
import spark.Spark;

public class CORS {

  public static void enable(final String origin, final String headers, final String methods) {
    Spark.options(
        "/*",
        (request, response) -> {
          String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
          if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
          }

          String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
          if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
          }

          return HttpStatus.OK_200;
        });

    Spark.before(
        (request, response) -> {
          response.header("Access-Control-Allow-Headers", headers);
          response.header("Access-Control-Request-Method", methods);
          response.header("Access-Control-Allow-Origin", origin);
          response.type("application/json");
        });
  }
}
