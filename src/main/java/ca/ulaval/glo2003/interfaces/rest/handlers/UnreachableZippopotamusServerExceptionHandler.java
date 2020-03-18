package ca.ulaval.glo2003.interfaces.rest.handlers;

import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import ca.ulaval.glo2003.interfaces.rest.exceptions.UnreachableZippopotamusServerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

public class UnreachableZippopotamusServerExceptionHandler
    implements ExceptionHandler<UnreachableZippopotamusServerException> {

  @Override
  public void handle(UnreachableZippopotamusServerException e, Request request, Response response) {
    response.status(HttpStatus.NOT_FOUND_404);

    try {
      response.body(badRequest());
    } catch (JsonProcessingException ex) {
      // Ain't happening
      // TODO : False.
    }
  }

  private String badRequest() throws JsonProcessingException {
    ErrorResponse response =
        new ErrorResponse("UNREACHABLE_ZIPPOPOTAMUS_SERVER", "zippopotamus server unreachable");
    return new ObjectMapper().writeValueAsString(response);
  }
}
