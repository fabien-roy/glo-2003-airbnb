package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.InvalidColonySizeException;

class InvalidColonySizeErrorFactoryTest extends BookingErrorFactoryTest {

  public InvalidColonySizeErrorFactoryTest() {
    super(new InvalidColonySizeException());
    errorFactory = new InvalidColonySizeErrorFactory();
  }
}
