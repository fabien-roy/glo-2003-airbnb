package ca.ulaval.glo2003.bookings.rest.factories;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.errors.rest.factories.AbstractErrorFactoryTest;

public abstract class BookingErrorFactoryTest extends AbstractErrorFactoryTest<BookingException> {

  protected BookingErrorFactoryTest(BookingException associatedException) {
    super(associatedException, mock(BookingException.class));
  }
}
