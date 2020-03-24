package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler implements ExceptionHandler<Exception> {

  private final Set<ErrorFactory> factories;
  private final DefaultErrorFactory defaultFactory;

  @Inject
  public CatchallExceptionHandler(Set<ErrorFactory> factories, DefaultErrorFactory defaultFactory) {
    this.defaultFactory = defaultFactory;
    this.factories = factories;
  }

  @Override
  public void handle(Exception e, Request request, Response response) {
    String errorResponse;
    int status;

    Optional<ErrorFactory> foundFactory =
        factories.stream().filter(factory -> factory.canHandle(e)).findFirst();

    if (foundFactory.isPresent()) {
      status = foundFactory.get().createStatus();
      errorResponse = foundFactory.get().createResponse();
    } else {
      status = defaultFactory.createStatus();
      errorResponse = defaultFactory.createResponse();
    }

    response.status(status);
    response.body(errorResponse);
  }
}
