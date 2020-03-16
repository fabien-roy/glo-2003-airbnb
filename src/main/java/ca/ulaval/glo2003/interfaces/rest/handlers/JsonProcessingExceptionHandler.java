package ca.ulaval.glo2003.interfaces.rest.handlers;

import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class JsonProcessingExceptionHandler implements ExceptionHandler<JsonProcessingException> {

  @Override
  public void handle(JsonProcessingException e, Request request, Response response) {
    response.status(HttpStatus.BAD_REQUEST_400);

    try {
      response.body(badRequest());
    } catch (JsonProcessingException ex) {
      // Ain't happening
    }
  }

  private String badRequest() throws JsonProcessingException {
    ErrorResponse response = new ErrorResponse("BAD_REQUEST", "could not parse JSON");
    return new ObjectMapper().writeValueAsString(response);
  }
}
