package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackagesException;

class InvalidPackagesErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidPackagesErrorFactoryTest() {
    super(new InvalidPackagesException());
    errorFactory = new InvalidPackagesErrorFactory();
  }
}
