package ca.ulaval.glo2003;

import spark.Spark;

public class Server {

  private static final String CORS_HEADERS =
      "Content-Type,Authorization,X-Requested-With,Content-Length, Accept,Origin";
  private static final String CORS_METHODS = "GET,PUT,POST,DELETE,OPTIONS";

  public void start(int portNumber) {
    Spark.port(portNumber);
    Router.setUpRoutes();
    CORS.enable("*", CORS_HEADERS, CORS_METHODS);
  }

  public void stop() {
    Spark.stop();
  }
}
