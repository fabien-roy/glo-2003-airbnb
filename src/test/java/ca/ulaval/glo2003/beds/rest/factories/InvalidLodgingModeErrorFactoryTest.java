package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidLodgingModeException;

class InvalidLodgingModeErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidLodgingModeErrorFactoryTest() {
    super(new InvalidLodgingModeException());
    errorFactory = new InvalidLodgingModeErrorFactory();
  }
}
