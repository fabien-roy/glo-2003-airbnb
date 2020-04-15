package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;

class MissingColonySizeErrorFactoryTest extends BedErrorFactoryTest {

  public MissingColonySizeErrorFactoryTest() {
    super(new MissingColonySizeException());
    errorFactory = new MissingColonySizeErrorFactory();
  }
}
