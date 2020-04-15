package ca.ulaval.glo2003.locations.rest.factories;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.errors.rest.factories.AbstractErrorFactoryTest;
import ca.ulaval.glo2003.locations.exceptions.LocationException;

public abstract class LocationErrorFactoryTest extends AbstractErrorFactoryTest<LocationException> {

  protected LocationErrorFactoryTest(LocationException associatedException) {
    super(associatedException, mock(LocationException.class));
  }
}
