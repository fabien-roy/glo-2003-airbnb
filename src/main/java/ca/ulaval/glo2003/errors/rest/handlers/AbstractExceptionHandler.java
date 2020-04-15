package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.Set;
import spark.ExceptionHandler;
import spark.Response;

public abstract class AbstractExceptionHandler<E extends Exception> implements ExceptionHandler<E> {

  private final ObjectMapper objectMapper;

  protected AbstractExceptionHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  protected void handleIfCan(Set<ErrorFactory<E>> factories, E exception, Response response) {
    Optional<ErrorFactory<E>> foundFactory = findFactory(factories, exception);

    foundFactory.ifPresent(factory -> setResponse(response, factory));
  }

  protected Optional<ErrorFactory<E>> findFactory(Set<ErrorFactory<E>> factories, E exception) {
    return factories.stream().filter(factory -> factory.canHandle(exception)).findFirst();
  }

  protected void setResponse(Response response, ErrorFactory<E> errorFactory) {
    int status = errorFactory.createStatus();
    ErrorResponse errorResponse = errorFactory.createResponse();

    setResponse(response, status, errorResponse);
  }

  protected void setResponse(Response response, int status, ErrorResponse errorResponse) {
    response.status(status);
    response.body(tryWriteBody(errorResponse));
  }

  private String tryWriteBody(ErrorResponse errorResponse) {
    try {
      return objectMapper.writeValueAsString(errorResponse);
    } catch (JsonProcessingException ex) {
      return "Error processing body";
    }
  }
}
