package ca.ulaval.glo2003.interfaces.clients.handler;

import ca.ulaval.glo2003.interfaces.clients.exceptions.InvalidZipCodeException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class InvalidZipCodeExceptionHandler implements ExceptionHandler<InvalidZipCodeException> {

  @Override
  public void handle(InvalidZipCodeException e, Request request, Response response) {
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
        new ErrorResponse("INVALID_ZIP_CODE", "zip code should be a 5 digits number");
    return new ObjectMapper().writeValueAsString(response);
  }
}
