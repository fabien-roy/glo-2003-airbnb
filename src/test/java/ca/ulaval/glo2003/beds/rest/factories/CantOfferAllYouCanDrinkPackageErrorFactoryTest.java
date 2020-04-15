package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;

class CantOfferAllYouCanDrinkPackageErrorFactoryTest extends BedErrorFactoryTest {

  public CantOfferAllYouCanDrinkPackageErrorFactoryTest() {
    super(new AllYouCanDrinkDependencyException());
    errorFactory = new CantOfferAllYouCanDrinkPackageErrorFactory();
  }
}
