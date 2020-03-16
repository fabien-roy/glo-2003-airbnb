package ca.ulaval.glo2003.interfaces.rest.handlers;

import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class InvalidFormatExceptionHandler implements ExceptionHandler<InvalidFormatException> {

  @Override
  public void handle(InvalidFormatException e, Request request, Response response) {
    response.status(HttpStatus.BAD_REQUEST_400);

    try {
      response.body(badRequest());
    } catch (JsonProcessingException ex) {
      // Ain't happening
    }
  }

  private String badRequest() throws JsonProcessingException {
    ErrorResponse response = new ErrorResponse("INVALID_FORMAT", "invalid format");
    return new ObjectMapper().writeValueAsString(response);
  }
}
