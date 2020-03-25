package ca.ulaval.glo2003.locations.rest.handlers;

import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandler;
import ca.ulaval.glo2003.locations.exceptions.LocationException;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class LocationExceptionHandler extends AbstractExceptionHandler<LocationException> {

  private final Set<ErrorFactory<LocationException>> factories;

  @Inject
  public LocationExceptionHandler(Set<ErrorFactory<LocationException>> factories) {
    this.factories = factories;
  }

  @Override
  public void handle(LocationException e, Request request, Response response) {
    handleIfCan(factories, e, response);
  }
}
