package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.rest.factories.BedErrorResponseFactory;
import ca.ulaval.glo2003.beds.rest.factories.BedErrorStatusFactory;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class BedExceptionHandler implements ExceptionHandler<BedException> {

  private final BedErrorStatusFactory bedErrorStatusFactory;
  private final BedErrorResponseFactory bedErrorResponseFactory;

  @Inject
  public BedExceptionHandler(
      BedErrorStatusFactory bedErrorStatusFactory,
      BedErrorResponseFactory bedErrorResponseFactory) {
    this.bedErrorStatusFactory = bedErrorStatusFactory;
    this.bedErrorResponseFactory = bedErrorResponseFactory;
  }

  @Override
  public void handle(BedException e, Request request, Response response) {
    response.status(bedErrorStatusFactory.create(e));
    response.body(bedErrorResponseFactory.create(e));
  }
}
