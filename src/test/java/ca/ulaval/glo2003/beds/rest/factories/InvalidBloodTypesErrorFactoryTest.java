package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;

class InvalidBloodTypesErrorFactoryTest extends BedErrorFactoryTest {

  public InvalidBloodTypesErrorFactoryTest() {
    super(new InvalidBloodTypesException());
    errorFactory = new InvalidBloodTypesErrorFactory();
  }
}
