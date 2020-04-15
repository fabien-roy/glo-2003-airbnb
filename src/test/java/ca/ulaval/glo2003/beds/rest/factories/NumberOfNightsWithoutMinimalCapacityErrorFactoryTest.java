package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.NumberOfNightsWithoutMinimalCapacityException;

class NumberOfNightsWithoutMinimalCapacityErrorFactoryTest extends BedErrorFactoryTest {

  public NumberOfNightsWithoutMinimalCapacityErrorFactoryTest() {
    super(new NumberOfNightsWithoutMinimalCapacityException());
    errorFactory = new NumberOfNightsWithoutMinimalCapacityErrorFactory();
  }
}
