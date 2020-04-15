package ca.ulaval.glo2003.locations.rest.handlers;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandlerTest;
import ca.ulaval.glo2003.locations.exceptions.LocationException;

public class LocationExceptionHandlerTest extends AbstractExceptionHandlerTest<LocationException> {

  public LocationExceptionHandlerTest() {
    super(mock(LocationException.class));
  }

  @Override
  protected void resetFactories() {
    exceptionHandler = new LocationExceptionHandler(objectMapper, factories);
  }
}
