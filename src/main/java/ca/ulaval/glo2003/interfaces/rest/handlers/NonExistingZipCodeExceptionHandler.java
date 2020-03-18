package ca.ulaval.glo2003.interfaces.rest.handlers;

import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import ca.ulaval.glo2003.interfaces.rest.exceptions.NonExistingZipCodeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class NonExistingZipCodeExceptionHandler
    implements ExceptionHandler<NonExistingZipCodeException> {

  @Override
  public void handle(NonExistingZipCodeException e, Request request, Response response) {
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
        new ErrorResponse("NON_EXISTING_ZIP_CODE", "zip code is not an existing US postal code");
    return new ObjectMapper().writeValueAsString(response);
  }
}
