package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.BedNotFoundException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class BedNotFoundExceptionHandler implements ExceptionHandler<BedNotFoundException> {

  @Override
  public void handle(BedNotFoundException e, Request request, Response response) {
    response.status(HttpStatus.NOT_FOUND_404);

    try {
      response.body(badRequest(e.getBedNumber()));
    } catch (JsonProcessingException ex) {
      // Ain't happening
      // TODO : False.
    }
  }

  private String badRequest(String number) throws JsonProcessingException {
    ErrorResponse response =
        new ErrorResponse("BED_NOT_FOUND", "bed with number " + number + " could not be found");
    return new ObjectMapper().writeValueAsString(response);
  }
}
