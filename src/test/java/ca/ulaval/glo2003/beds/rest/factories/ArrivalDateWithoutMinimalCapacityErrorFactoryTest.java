package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.ArrivalDateWithoutMinimalCapacityException;

public class ArrivalDateWithoutMinimalCapacityErrorFactoryTest extends BedErrorFactoryTest {

  public ArrivalDateWithoutMinimalCapacityErrorFactoryTest() {
    super(new ArrivalDateWithoutMinimalCapacityException());
    errorFactory = new ArrivalDateWithoutMinimalCapacityErrorFactory();
  }
}
