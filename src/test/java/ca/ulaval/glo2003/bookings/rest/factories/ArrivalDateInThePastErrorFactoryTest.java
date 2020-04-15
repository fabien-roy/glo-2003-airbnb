package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;

class ArrivalDateInThePastErrorFactoryTest extends BookingErrorFactoryTest {

  public ArrivalDateInThePastErrorFactoryTest() {
    super(new ArrivalDateInThePastException());
    errorFactory = new ArrivalDateInThePastErrorFactory();
  }
}
