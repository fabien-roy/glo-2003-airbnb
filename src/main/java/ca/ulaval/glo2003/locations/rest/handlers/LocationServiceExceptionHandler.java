package ca.ulaval.glo2003.locations.rest.handlers;

import ca.ulaval.glo2003.locations.exceptions.LocationServiceException;
import ca.ulaval.glo2003.locations.rest.factories.LocationServiceErrorResponseFactory;
import ca.ulaval.glo2003.locations.rest.factories.LocationServiceErrorStatusFactory;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class LocationServiceExceptionHandler implements ExceptionHandler<LocationServiceException> {

  private final LocationServiceErrorStatusFactory locationServiceErrorStatusFactory;
  private final LocationServiceErrorResponseFactory locationServiceErrorResponseFactory;

  @Inject
  public LocationServiceExceptionHandler(
      LocationServiceErrorStatusFactory locationServiceErrorStatusFactory,
      LocationServiceErrorResponseFactory locationServiceErrorResponseFactory) {
    this.locationServiceErrorStatusFactory = locationServiceErrorStatusFactory;
    this.locationServiceErrorResponseFactory = locationServiceErrorResponseFactory;
  }

  @Override
  public void handle(LocationServiceException e, Request request, Response response) {
    response.status(locationServiceErrorStatusFactory.create(e));
    response.body(locationServiceErrorResponseFactory.create(e));
  }
}
