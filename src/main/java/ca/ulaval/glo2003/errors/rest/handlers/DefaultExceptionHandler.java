package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;
import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.Set;
import spark.Response;

public abstract class DefaultExceptionHandler<E extends Exception>
    extends AbstractExceptionHandler<E> {

  private final DefaultErrorFactory defaultFactory;

  public DefaultExceptionHandler(ObjectMapper objectMapper, DefaultErrorFactory defaultFactory) {
    super(objectMapper);
    this.defaultFactory = defaultFactory;
  }

  @Override
  protected void handleIfCan(Set<ErrorFactory<E>> factories, E exception, Response response) {
    Optional<ErrorFactory<E>> foundFactory = findFactory(factories, exception);

    if (foundFactory.isPresent()) {
      setResponse(response, foundFactory.get());
    } else {
      int status = defaultFactory.createStatus();
      ErrorResponse errorResponse = defaultFactory.createResponse();
      setResponse(response, status, errorResponse);
    }
  }
}
