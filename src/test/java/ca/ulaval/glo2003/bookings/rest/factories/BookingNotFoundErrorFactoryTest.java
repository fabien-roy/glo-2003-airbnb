package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;

class BookingNotFoundErrorFactoryTest extends BookingErrorFactoryTest {

  public BookingNotFoundErrorFactoryTest() {
    super(new BookingNotFoundException("bookingNumber"));
    errorFactory = new BookingNotFoundErrorFactory();
  }
}
