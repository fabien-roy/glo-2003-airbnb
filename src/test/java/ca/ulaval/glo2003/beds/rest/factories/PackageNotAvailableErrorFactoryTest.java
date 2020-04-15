package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.PackageNotAvailableException;

class PackageNotAvailableErrorFactoryTest extends BedErrorFactoryTest {

  public PackageNotAvailableErrorFactoryTest() {
    super(new PackageNotAvailableException());
    errorFactory = new PackageNotAvailableErrorFactory();
  }
}
