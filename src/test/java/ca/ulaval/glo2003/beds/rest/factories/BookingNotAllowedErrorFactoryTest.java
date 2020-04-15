package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BookingNotAllowedException;

class BookingNotAllowedErrorFactoryTest extends BedErrorFactoryTest {

  public BookingNotAllowedErrorFactoryTest() {
    super(new BookingNotAllowedException());
    errorFactory = new BookingNotAllowedErrorFactory();
  }
}
