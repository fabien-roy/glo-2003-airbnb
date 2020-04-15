package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.locations.exceptions.InvalidZipCodeException;

class InvalidZipCodeErrorFactoryTest extends LocationErrorFactoryTest {

  public InvalidZipCodeErrorFactoryTest() {
    super(new InvalidZipCodeException());
    errorFactory = new InvalidZipCodeErrorFactory();
  }
}
