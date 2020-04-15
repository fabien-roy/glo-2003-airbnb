package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;

class BedNotFoundErrorFactoryTest extends BedErrorFactoryTest {

  public BedNotFoundErrorFactoryTest() {
    super(new BedNotFoundException("bedNumber"));
    errorFactory = new BedNotFoundErrorFactory();
  }
}
