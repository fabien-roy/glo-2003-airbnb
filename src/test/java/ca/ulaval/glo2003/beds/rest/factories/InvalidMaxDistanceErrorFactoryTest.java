package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;

class InvalidMaxDistanceErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidMaxDistanceErrorFactoryTest() {
    super(new InvalidMaxDistanceException());
    errorFactory = new InvalidMaxDistanceErrorFactory();
  }
}
