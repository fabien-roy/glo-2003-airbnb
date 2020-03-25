package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler extends AbstractExceptionHandler<Exception> {

  private final Set<CatchallErrorFactory> factories;

  @Inject
  public CatchallExceptionHandler(
      DefaultErrorFactory defaultFactory, Set<CatchallErrorFactory> factories) {
    super(defaultFactory);
    this.factories = factories;
  }

  @Override
  public void handle(Exception e, Request request, Response response) {
    Optional<CatchallErrorFactory> foundFactory =
        factories.stream().filter(factory -> factory.canHandle(e)).findFirst();

    if (foundFactory.isPresent()) {
      int status = foundFactory.get().createStatus();
      String errorResponse = foundFactory.get().createResponse();

      response.status(status);
      response.body(errorResponse);
    } else {
      super.handle(e, request, response);
    }
  }
}
