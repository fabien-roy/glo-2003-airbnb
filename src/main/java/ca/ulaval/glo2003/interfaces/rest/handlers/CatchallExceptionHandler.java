package ca.ulaval.glo2003.interfaces.rest.handlers;

import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorResponseFactory;
import ca.ulaval.glo2003.interfaces.rest.factories.CatchallErrorStatusFactory;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler implements ExceptionHandler<Exception> {

  private final CatchallErrorStatusFactory catchallErrorStatusFactory;
  private final CatchallErrorResponseFactory catchallErrorResponseFactory;

  public CatchallExceptionHandler(
      CatchallErrorStatusFactory catchallErrorStatusFactory,
      CatchallErrorResponseFactory catchallErrorResponseFactory) {
    this.catchallErrorStatusFactory = catchallErrorStatusFactory;
    this.catchallErrorResponseFactory = catchallErrorResponseFactory;
  }

  @Override
  public void handle(Exception e, Request request, Response response) {
    response.status(catchallErrorStatusFactory.create(e));
    response.body(catchallErrorResponseFactory.create(e));
  }
}
