package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import java.util.Optional;
import java.util.Set;
import spark.Response;

public abstract class DefaultExceptionHandler<E extends Exception>
    extends AbstractExceptionHandler<E> {

  private final DefaultErrorFactory defaultFactory;

  public DefaultExceptionHandler(DefaultErrorFactory defaultFactory) {
    this.defaultFactory = defaultFactory;
  }

  @Override
  protected void handleIfCan(Set<ErrorFactory<E>> factories, E exception, Response response) {
    Optional<ErrorFactory<E>> foundFactory = findFactory(factories, exception);

    if (foundFactory.isPresent()) {
      setResponse(response, foundFactory.get());
    } else {
      int status = defaultFactory.createStatus();
      String errorResponse = defaultFactory.createResponse();
      setResponse(response, status, errorResponse);
    }
  }
}
