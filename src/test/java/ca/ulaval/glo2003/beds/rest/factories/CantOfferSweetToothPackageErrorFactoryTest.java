package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;

class CantOfferSweetToothPackageErrorFactoryTest extends BedErrorFactoryTest {

  public CantOfferSweetToothPackageErrorFactoryTest() {
    super(new SweetToothDependencyException());
    errorFactory = new CantOfferSweetToothPackageErrorFactory();
  }
}
