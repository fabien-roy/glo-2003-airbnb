package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.SweetToothDependencyException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class SweetToothDependencyExceptionHandler
    implements ExceptionHandler<SweetToothDependencyException> {

  @Override
  public void handle(SweetToothDependencyException e, Request request, Response response) {
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
            "CANT_OFFER_SWEET_TOOTH_PACKAGE",
            "in order to offer sweetTooth package, you must also offer the bloodthirsty and allYouCanDrink packages");
    return new ObjectMapper().writeValueAsString(response);
  }
}
