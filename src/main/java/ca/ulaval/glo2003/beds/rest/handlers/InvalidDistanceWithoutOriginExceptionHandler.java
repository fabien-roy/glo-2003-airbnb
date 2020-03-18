package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidDistanceWithoutOriginException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class InvalidDistanceWithoutOriginExceptionHandler implements ExceptionHandler<InvalidDistanceWithoutOriginException> {

  @Override
  public void handle(InvalidDistanceWithoutOriginException e, Request request, Response response) {
    response.status(HttpStatus.BAD_REQUEST_400);

    try {
      response.body(badRequest());
    } catch (JsonProcessingException ex) {
      // Ain't happening
    }
  }

  private String badRequest() throws JsonProcessingException {
    ErrorResponse response =
        new ErrorResponse("MAX_DISTANCE_WITHOUT_ORIGIN", "an origin point should be provided along with the \n" +
                "     maximum distance");
    return new ObjectMapper().writeValueAsString(response);
  }
}
