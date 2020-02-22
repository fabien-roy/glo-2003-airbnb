package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidMinimalCapacityException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class InvalidMinimalCapacityExceptionHandler implements ExceptionHandler<InvalidMinimalCapacityException> {

  @Override
  public void handle(InvalidMinimalCapacityException e, Request request, Response response) {
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
        new ErrorResponse(
            "INVALID_MINIMAL_CAPACITY", "minimal capacity should be a positive number");
    return new ObjectMapper().writeValueAsString(response);
  }
}
