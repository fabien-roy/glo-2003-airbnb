package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.DefaultErrorFactory;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler extends AbstractExceptionHandler<Exception> {

  private final DefaultErrorFactory defaultFactory;

  @Inject
  public CatchallExceptionHandler(DefaultErrorFactory defaultFactory) {
    this.defaultFactory = defaultFactory;
  }

  @Override
  public void handle(Exception exception, Request request, Response response) {
    int status = defaultFactory.createStatus();
    String errorResponse = defaultFactory.createResponse();
    setResponse(response, status, errorResponse);
  }
}
