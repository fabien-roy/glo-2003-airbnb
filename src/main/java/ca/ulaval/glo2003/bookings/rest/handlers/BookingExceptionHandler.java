package ca.ulaval.glo2003.bookings.rest.handlers;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.rest.factories.BookingErrorFactory;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class BookingExceptionHandler implements ExceptionHandler<BookingException> {

  private final Set<BookingErrorFactory> factories;

  @Inject
  public BookingExceptionHandler(Set<BookingErrorFactory> factories) {
    this.factories = factories;
  }

  @Override
  public void handle(BookingException e, Request request, Response response) {
    String errorResponse;
    int status;

    Optional<BookingErrorFactory> foundFactory =
        factories.stream().filter(factory -> factory.canHandle(e)).findFirst();

    status = foundFactory.get().createStatus();
    errorResponse = foundFactory.get().createResponse();

    response.status(status);
    response.body(errorResponse);
  }
}
