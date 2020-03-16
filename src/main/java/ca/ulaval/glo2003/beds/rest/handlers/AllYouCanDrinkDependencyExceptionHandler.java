package ca.ulaval.glo2003.beds.rest.handlers;

import ca.ulaval.glo2003.beds.rest.exceptions.AllYouCanDrinkDependencyException;
import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class AllYouCanDrinkDependencyExceptionHandler
    implements ExceptionHandler<AllYouCanDrinkDependencyException> {

  @Override
  public void handle(AllYouCanDrinkDependencyException e, Request request, Response response) {
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
            "CANT_OFFER_ALL_YOU_CAN_DRINK_PACKAGE",
            "in order to offer allYouCanDrink package, you must also offer the bloodthirsty package");
    return new ObjectMapper().writeValueAsString(response);
  }
}
