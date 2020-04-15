package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;

class MaxDistanceWithoutOriginErrorFactoryTest extends BedErrorFactoryTest {

  public MaxDistanceWithoutOriginErrorFactoryTest() {
    super(new MaxDistanceWithoutOriginException());
    errorFactory = new MaxDistanceWithoutOriginErrorFactory();
  }
}
