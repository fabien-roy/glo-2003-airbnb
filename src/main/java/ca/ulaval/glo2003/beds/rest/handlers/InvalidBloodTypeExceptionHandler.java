package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBloodTypeException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class InvalidBloodTypeExceptionHandler
    implements ExceptionHandler<InvalidBloodTypeException> {

  @Override
  public void handle(InvalidBloodTypeException e, Request request, Response response) {
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
            "INVALID_BED_TYPE", "bed type should be one of latex, memoryFoam or springs");
    return new ObjectMapper().writeValueAsString(response);
  }
}
