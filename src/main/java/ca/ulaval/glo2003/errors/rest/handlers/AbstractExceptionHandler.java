package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public abstract class AbstractExceptionHandler<E extends Exception> implements ExceptionHandler<E> {

  private final DefaultErrorFactory defaultFactory;

  @Inject
  public AbstractExceptionHandler(DefaultErrorFactory defaultFactory) {
    this.defaultFactory = defaultFactory;
  }

  @Override
  public void handle(E e, Request request, Response response) {
    int status = defaultFactory.createStatus();
    String errorResponse = defaultFactory.createResponse();

    response.status(status);
    response.body(errorResponse);
  }
}
