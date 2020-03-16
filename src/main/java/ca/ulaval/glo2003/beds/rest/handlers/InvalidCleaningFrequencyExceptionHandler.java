package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidCleaningFrequencyException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class InvalidCleaningFrequencyExceptionHandler
    implements ExceptionHandler<InvalidCleaningFrequencyException> {

  @Override
  public void handle(InvalidCleaningFrequencyException e, Request request, Response response) {
    response.status(HttpStatus.BAD_REQUEST_400);

    try {
      response.body(badRequest());
    } catch (JsonProcessingException ex) {
      // Ain't happening
    }
  }

  private String badRequest() throws JsonProcessingException {
    ErrorResponse response =
        new ErrorResponse(
            "INVALID_CLEANING_FREQUENCY",
            "cleaning frequency should be one of weekly, monthly, annual or never");
    return new ObjectMapper().writeValueAsString(response);
  }
}
