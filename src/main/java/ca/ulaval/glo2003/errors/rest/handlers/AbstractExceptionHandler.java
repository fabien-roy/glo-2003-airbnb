package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import java.util.Optional;
import java.util.Set;
import spark.ExceptionHandler;
import spark.Response;

public abstract class AbstractExceptionHandler<E extends Exception> implements ExceptionHandler<E> {

  protected void handleIfCan(Set<ErrorFactory<E>> factories, E exception, Response response) {
    Optional<ErrorFactory<E>> foundFactory = findFactory(factories, exception);

    foundFactory.ifPresent(factory -> setResponse(response, factory));
  }

  protected Optional<ErrorFactory<E>> findFactory(Set<ErrorFactory<E>> factories, E exception) {
    return factories.stream().filter(factory -> factory.canHandle(exception)).findFirst();
  }

  protected void setResponse(Response response, ErrorFactory<E> errorFactory) {
    int status = errorFactory.createStatus();
    String errorResponse = errorFactory.createResponse();

    response.status(status);
    response.body(errorResponse);
  }
}
