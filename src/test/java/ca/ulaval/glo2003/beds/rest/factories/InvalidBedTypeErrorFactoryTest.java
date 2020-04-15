package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;

class InvalidBedTypeErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidBedTypeErrorFactoryTest() {
    super(new InvalidBedTypeException());
    errorFactory = new InvalidBedTypeErrorFactory();
  }
}
