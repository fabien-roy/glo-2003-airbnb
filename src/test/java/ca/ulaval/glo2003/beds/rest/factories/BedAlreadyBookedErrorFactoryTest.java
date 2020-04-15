package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;

class BedAlreadyBookedErrorFactoryTest extends BedErrorFactoryTest {

  public BedAlreadyBookedErrorFactoryTest() {
    super(new BedAlreadyBookedException());
    errorFactory = new BedAlreadyBookedErrorFactory();
  }
}
