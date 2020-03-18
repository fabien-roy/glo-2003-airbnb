package ca.ulaval.glo2003.interfaces.rest.factories;

import ca.ulaval.glo2003.interfaces.rest.ErrorResponse;
import ca.ulaval.glo2003.interfaces.rest.exceptions.InvalidFormatException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CatchallErrorResponseFactory {

  public String create(Exception exception) {
    if (exception instanceof InvalidFormatException) {
      return invalidFormat();
    } else if (exception instanceof JsonProcessingException) {
      return couldNotParseJson();
    } else {
      return defaultResponse();
    }
  }

  protected String tryWriteValueAsString(String error, String description) {
    try {
      ErrorResponse response = new ErrorResponse(error, description);
      return new ObjectMapper().writeValueAsString(response);
    } catch (JsonProcessingException ex) {
      return null;
    }
  }

  protected String defaultResponse() {
    return tryWriteValueAsString("BAD_REQUEST", "something went wrong");
  }

  private String invalidFormat() {
    return tryWriteValueAsString("INVALID_FORMAT", "invalid format");
  }

  private String couldNotParseJson() {
    return tryWriteValueAsString("BAD_REQUEST", "could not parse JSON");
  }
}
