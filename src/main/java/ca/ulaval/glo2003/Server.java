package ca.ulaval.glo2003;

import spark.Spark;

public class Server {

  public void start(int portNumber) {
    Spark.port(portNumber);
  }

  public void stop() {
    Spark.stop();
  }
}
