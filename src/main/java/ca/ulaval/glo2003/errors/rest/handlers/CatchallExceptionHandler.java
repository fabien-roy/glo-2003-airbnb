package ca.ulaval.glo2003.errors.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorResponseFactory;
import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorStatusFactory;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class CatchallExceptionHandler implements ExceptionHandler<Exception> {

  private final CatchallErrorStatusFactory catchallErrorStatusFactory;
  private final CatchallErrorResponseFactory catchallErrorResponseFactory;

  @Inject
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
