package ca.ulaval.glo2003.locations.rest.handlers;

import ca.ulaval.glo2003.locations.exceptions.LocationServiceException;
import ca.ulaval.glo2003.locations.rest.factories.LocationErrorFactory;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class LocationExceptionHandler implements ExceptionHandler<LocationServiceException> {

  private final Set<LocationErrorFactory> factories;

  @Inject
  public LocationExceptionHandler(Set<LocationErrorFactory> factories) {
    this.factories = factories;
  }

  // TODO : Refactor this
  @Override
  public void handle(LocationServiceException e, Request request, Response response) {
    String errorResponse;
    int status;

    Optional<LocationErrorFactory> foundFactory =
        factories.stream().filter(factory -> factory.canHandle(e)).findFirst();

    status = foundFactory.get().createStatus();
    errorResponse = foundFactory.get().createResponse();

    response.status(status);
    response.body(errorResponse);
  }
}
