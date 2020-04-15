package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidPackageException;

class InvalidPackageErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidPackageErrorFactoryTest() {
    super(new InvalidPackageException());
    errorFactory = new InvalidPackageErrorFactory();
  }
}
