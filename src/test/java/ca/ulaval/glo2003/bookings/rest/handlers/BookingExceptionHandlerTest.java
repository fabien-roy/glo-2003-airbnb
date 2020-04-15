package ca.ulaval.glo2003.bookings.rest.handlers;

import static org.mockito.Mockito.mock;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandlerTest;

public class BookingExceptionHandlerTest extends AbstractExceptionHandlerTest<BookingException> {

  public BookingExceptionHandlerTest() {
    super(mock(BookingException.class));
  }

  @Override
  protected void resetFactories() {
    exceptionHandler = new BookingExceptionHandler(objectMapper, factories);
  }
}
