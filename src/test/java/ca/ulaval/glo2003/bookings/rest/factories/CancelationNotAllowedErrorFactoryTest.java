package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.CancelationNotAllowedException;

class CancelationNotAllowedErrorFactoryTest extends BookingErrorFactoryTest {

  public CancelationNotAllowedErrorFactoryTest() {
    super(new CancelationNotAllowedException());
    errorFactory = new CancelationNotAllowedErrorFactory();
  }
}
