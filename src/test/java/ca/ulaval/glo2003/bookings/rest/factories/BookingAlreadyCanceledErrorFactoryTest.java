package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;

class BookingAlreadyCanceledErrorFactoryTest extends BookingErrorFactoryTest {

  public BookingAlreadyCanceledErrorFactoryTest() {
    super(new BookingAlreadyCanceledException());
    errorFactory = new BookingAlreadyCanceledErrorFactory();
  }
}
