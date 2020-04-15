package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.InvalidNumberOfNightsException;

class InvalidNumberOfNightsErrorFactoryTest extends BookingErrorFactoryTest {

  public InvalidNumberOfNightsErrorFactoryTest() {
    super(new InvalidNumberOfNightsException());
    errorFactory = new InvalidNumberOfNightsErrorFactory();
  }
}
