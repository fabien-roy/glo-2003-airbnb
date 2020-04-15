package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;

class UnreachableZippopotamusServerErrorFactoryTest extends LocationErrorFactoryTest {

  public UnreachableZippopotamusServerErrorFactoryTest() {
    super(new UnreachableZippopotamusServerException());
    errorFactory = new UnreachableZippopotamusServerErrorFactory();
  }
}
