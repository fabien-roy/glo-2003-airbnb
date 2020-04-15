package ca.ulaval.glo2003.beds.rest.factories;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.errors.rest.factories.AbstractErrorFactoryTest;

public abstract class BedErrorFactoryTest extends AbstractErrorFactoryTest<BedException> {

  protected BedErrorFactoryTest(BedException associatedException) {
    super(associatedException, mock(BedException.class));
  }
}
