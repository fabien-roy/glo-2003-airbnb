package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class ExceedingAccommodationCapacityExceptionHandler
    implements ExceptionHandler<ExceedingAccommodationCapacityException> {

  @Override
  public void handle(
      ExceedingAccommodationCapacityException e, Request request, Response response) {
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
            "INVALID_MINIMAL_CAPACITY",
            "maximal capacity of latex is 400, memory foam is 700 spring is 1000");
    return new ObjectMapper().writeValueAsString(response);
  }
}
