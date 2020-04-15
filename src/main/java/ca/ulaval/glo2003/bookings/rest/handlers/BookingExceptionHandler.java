package ca.ulaval.glo2003.bookings.rest.handlers;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.errors.rest.factories.ErrorFactory;
import ca.ulaval.glo2003.errors.rest.handlers.AbstractExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.inject.Inject;
import spark.Request;
import spark.Response;

public class BookingExceptionHandler extends AbstractExceptionHandler<BookingException> {

  private final Set<ErrorFactory<BookingException>> factories;

  @Inject
  public BookingExceptionHandler(
      ObjectMapper objectMapper, Set<ErrorFactory<BookingException>> factories) {
    super(objectMapper);
    this.factories = factories;
  }

  @Override
  public void handle(BookingException e, Request request, Response response) {
    handleIfCan(factories, e, response);
  }
}
