package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidMinimalCapacityException;

class InvalidMinimalCapacityErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidMinimalCapacityErrorFactoryTest() {
    super(new InvalidMinimalCapacityException());
    errorFactory = new InvalidMinimalCapacityErrorFactory();
  }
}
