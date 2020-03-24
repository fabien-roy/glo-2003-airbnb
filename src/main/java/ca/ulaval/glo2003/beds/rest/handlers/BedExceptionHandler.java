package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.rest.factories.BedErrorFactory;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class BedExceptionHandler implements ExceptionHandler<BedException> {

  private final Set<BedErrorFactory> factories;

  @Inject
  public BedExceptionHandler(Set<BedErrorFactory> factories) {
    this.factories = factories;
  }

  @Override
  public void handle(BedException e, Request request, Response response) {
    String errorResponse;
    int status;

    Optional<BedErrorFactory> foundFactory =
        factories.stream().filter(factory -> factory.canHandle(e)).findFirst();

    status = foundFactory.get().createStatus();
    errorResponse = foundFactory.get().createResponse();

    response.status(status);
    response.body(errorResponse);
  }
}
