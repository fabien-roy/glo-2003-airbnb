package ca.ulaval.glo2003.interfaces.rest.handlers;

import ca.ulaval.glo2003.interfaces.exceptions.ExternalServiceException;
import ca.ulaval.glo2003.interfaces.rest.factories.ExternalServiceErrorResponseFactory;
import ca.ulaval.glo2003.interfaces.rest.factories.ExternalServiceErrorStatusFactory;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class ExternalServiceExceptionHandler implements ExceptionHandler<ExternalServiceException> {

  private final ExternalServiceErrorStatusFactory externalServiceErrorStatusFactory;
  private final ExternalServiceErrorResponseFactory externalServiceErrorResponseFactory;

  @Inject
  public ExternalServiceExceptionHandler(
      ExternalServiceErrorStatusFactory externalServiceErrorStatusFactory,
      ExternalServiceErrorResponseFactory externalServiceErrorResponseFactory) {
    this.externalServiceErrorStatusFactory = externalServiceErrorStatusFactory;
    this.externalServiceErrorResponseFactory = externalServiceErrorResponseFactory;
  }

  @Override
  public void handle(ExternalServiceException e, Request request, Response response) {
    response.status(externalServiceErrorStatusFactory.create(e));
    response.body(externalServiceErrorResponseFactory.create(e));
  }
}
