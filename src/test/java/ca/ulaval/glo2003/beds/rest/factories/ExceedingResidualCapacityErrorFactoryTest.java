package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.ExceedingResidualCapacityException;

class ExceedingResidualCapacityErrorFactoryTest extends BedErrorFactoryTest {

  public ExceedingResidualCapacityErrorFactoryTest() {
    super(new ExceedingResidualCapacityException());
    errorFactory = new ExceedingResidualCapacityErrorFactory();
  }
}
