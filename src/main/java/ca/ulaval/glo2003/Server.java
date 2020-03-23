package ca.ulaval.glo2003;

import com.google.inject.Inject;
import spark.Spark;

public class Server {

  private static final String CORS_HEADERS =
      "Content-Type,Authorization,X-Requested-With,Content-Length, Accept,Origin";
  private static final String CORS_METHODS = "GET,PUT,POST,DELETE,OPTIONS";

  private final Router router;

  @Inject
  public Server(Router router) {
    this.router = router;
  }

  public void start(int portNumber) {
    Spark.port(portNumber);
    router.setUpRoutes();
    CORS.enable("*", CORS_HEADERS, CORS_METHODS);
  }

  public void stop() {
    Spark.stop();
  }
}
