package ca.ulaval.glo2003.beds.bookings.rest.handlers;

import ca.ulaval.glo2003.beds.bookings.rest.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class BookingNotFoundExceptionHandler implements ExceptionHandler<BookingNotFoundException> {

  @Override
  public void handle(BookingNotFoundException e, Request request, Response response) {
    response.status(HttpStatus.NOT_FOUND_404);

    try {
      response.body(badRequest(e.getBookingNumber()));
    } catch (JsonProcessingException ex) {
      // Ain't happening
    }
  }

  private String badRequest(String number) throws JsonProcessingException {
    ErrorResponse response =
        new ErrorResponse(
            "BOOKING_NOT_FOUND", "booking with number " + number + " could not be found");
    return new ObjectMapper().writeValueAsString(response);
  }
}
