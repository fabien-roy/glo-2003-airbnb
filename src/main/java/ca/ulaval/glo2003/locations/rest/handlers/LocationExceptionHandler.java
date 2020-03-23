package ca.ulaval.glo2003.locations.rest.handlers;

import ca.ulaval.glo2003.locations.exceptions.LocationException;
import ca.ulaval.glo2003.locations.rest.factories.LocationErrorResponseFactory;
import ca.ulaval.glo2003.locations.rest.factories.LocationErrorStatusFactory;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class LocationExceptionHandler implements ExceptionHandler<LocationException> {

  private final LocationErrorStatusFactory locationErrorStatusFactory;
  private final LocationErrorResponseFactory locationErrorResponseFactory;

  @Inject
  public LocationExceptionHandler(
      LocationErrorStatusFactory locationErrorStatusFactory,
      LocationErrorResponseFactory locationErrorResponseFactory) {
    this.locationErrorStatusFactory = locationErrorStatusFactory;
    this.locationErrorResponseFactory = locationErrorResponseFactory;
  }

  @Override
  public void handle(LocationException e, Request request, Response response) {
    response.status(locationErrorStatusFactory.create(e));
    response.body(locationErrorResponseFactory.create(e));
  }
}
