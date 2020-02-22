package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.InvalidBedTypeException;
import ca.ulaval.glo2003.beds.rest.exceptions.InvalidPackageException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class InvalidPackageExceptionHandler implements ExceptionHandler<InvalidPackageException> {

  @Override
  public void handle(InvalidPackageException e, Request request, Response response) {
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
            "INVALID_PACKAGE", "package should be one of\n" +
                "    bloodthirsty, allYouCanDrink, sweetTooth");
    return new ObjectMapper().writeValueAsString(response);
  }
}
