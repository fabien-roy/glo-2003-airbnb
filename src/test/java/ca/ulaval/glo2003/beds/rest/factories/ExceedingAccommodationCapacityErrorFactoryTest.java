package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;

class ExceedingAccommodationCapacityErrorFactoryTest extends BedErrorFactoryTest {

  public ExceedingAccommodationCapacityErrorFactoryTest() {
    super(new ExceedingAccommodationCapacityException());
    errorFactory = new ExceedingAccommodationCapacityErrorFactory();
  }
}
