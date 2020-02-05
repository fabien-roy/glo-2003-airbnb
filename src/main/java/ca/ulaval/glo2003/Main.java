package ca.ulaval.glo2003;

import java.util.Optional;

public class Main {

  private static final int DEFAULT_PORT = 4567;
  private static final String PORT_ENV_VAR = "PORT";
  private static final String PROVIDED_PORT_MESSAGE = "INFO: Using the provided server port (%d).";
  private static final String MISSING_PORT_WARNING_MESSAGE =
      "INFO: The server port could not be found with '%s' env var. "
          + "\nINFO: Using the default one (%d).";

  public static void main(String[] args) {
    Server server = new Server();
    server.start(retrievePortNumber());
  }

  private static Integer retrievePortNumber() {
    return Optional.ofNullable(System.getenv(PORT_ENV_VAR))
        .map(Main::useProvidedPort)
        .orElseGet(Main::useDefaultPort);
  }

  private static Integer useProvidedPort(String providedPortValue) {
    Integer providedPort = Integer.valueOf(providedPortValue);
    System.out.println(String.format(PROVIDED_PORT_MESSAGE, providedPort));

    return providedPort;
  }

  private static Integer useDefaultPort() {
    System.out.println(String.format(MISSING_PORT_WARNING_MESSAGE, PORT_ENV_VAR, DEFAULT_PORT));

    return DEFAULT_PORT;
  }
}
