package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;

class InvalidArrivalDateErrorFactoryTest extends BookingErrorFactoryTest {

  public InvalidArrivalDateErrorFactoryTest() {
    super(new InvalidArrivalDateException());
    errorFactory = new InvalidArrivalDateErrorFactory();
  }
}
