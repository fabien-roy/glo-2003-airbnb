package ca.ulaval.glo2003.beds.bookings.rest.handlers;

import ca.ulaval.glo2003.beds.bookings.rest.exceptions.BookingNotAllowedException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class BookingNotAllowedExceptionHandler
    implements ExceptionHandler<BookingNotAllowedException> {

  @Override
  public void handle(BookingNotAllowedException e, Request request, Response response) {
    response.status(HttpStatus.BAD_REQUEST_400);

    try {
      response.body(badRequest());
    } catch (JsonProcessingException ex) {
      // Ain't happening
      // TODO : False.
    }
  }

  private String badRequest() throws JsonProcessingException {
    ErrorResponse response =
        new ErrorResponse("BOOKING_NOT_ALLOWED", "bed owner cannot book its own bed");
    return new ObjectMapper().writeValueAsString(response);
  }
}
