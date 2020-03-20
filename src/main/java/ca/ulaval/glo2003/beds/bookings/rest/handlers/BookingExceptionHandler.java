package ca.ulaval.glo2003.beds.bookings.rest.handlers;

import ca.ulaval.glo2003.beds.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.beds.bookings.rest.factories.BookingErrorResponseFactory;
import ca.ulaval.glo2003.beds.bookings.rest.factories.BookingErrorStatusFactory;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class BookingExceptionHandler implements ExceptionHandler<BookingException> {

  private final BookingErrorStatusFactory bookingErrorStatusFactory;
  private final BookingErrorResponseFactory bookingErrorResponseFactory;

  @Inject
  public BookingExceptionHandler(
      BookingErrorStatusFactory bookingErrorStatusFactory,
      BookingErrorResponseFactory bookingErrorResponseFactory) {
    this.bookingErrorStatusFactory = bookingErrorStatusFactory;
    this.bookingErrorResponseFactory = bookingErrorResponseFactory;
  }

  @Override
  public void handle(BookingException e, Request request, Response response) {
    response.status(bookingErrorStatusFactory.create(e));
    response.body(bookingErrorResponseFactory.create(e));
  }
}
