package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;

class InvalidCapacityErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidCapacityErrorFactoryTest() {
    super(new InvalidCapacityException());
    errorFactory = new InvalidCapacityErrorFactory();
  }
}
