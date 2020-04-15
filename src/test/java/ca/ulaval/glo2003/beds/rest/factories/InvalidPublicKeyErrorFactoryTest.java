package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;

class InvalidPublicKeyErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidPublicKeyErrorFactoryTest() {
    super(new InvalidPublicKeyException());
    errorFactory = new InvalidPublicKeyErrorFactory();
  }
}
