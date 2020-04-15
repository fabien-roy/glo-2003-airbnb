package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.locations.exceptions.NonExistingZipCodeException;

class NonExistingZipCodeErrorFactoryTest extends LocationErrorFactoryTest {

  public NonExistingZipCodeErrorFactoryTest() {
    super(new NonExistingZipCodeException());
    errorFactory = new NonExistingZipCodeErrorFactory();
  }
}
