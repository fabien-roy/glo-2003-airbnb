package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;

class InvalidCleaningFrequencyErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidCleaningFrequencyErrorFactoryTest() {
    super(new InvalidCleaningFrequencyException());
    errorFactory = new InvalidCleaningFrequencyErrorFactory();
  }
}
