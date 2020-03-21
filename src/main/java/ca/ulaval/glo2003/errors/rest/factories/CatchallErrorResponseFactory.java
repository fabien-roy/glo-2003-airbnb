package ca.ulaval.glo2003.errors.rest.factories;

import ca.ulaval.glo2003.errors.exceptions.InvalidFormatException;
import ca.ulaval.glo2003.errors.rest.ErrorResponse;
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

  protected static String tryWriteValueAsString(
      java.lang.String error, java.lang.String description) {
    try {
      ErrorResponse response = new ErrorResponse(error, description);
      return new ObjectMapper().writeValueAsString(response);
    } catch (JsonProcessingException ex) {
      return null;
    }
  }

  protected static String defaultResponse() {
    return tryWriteValueAsString("BAD_REQUEST", "something went wrong");
  }

  static String invalidFormat() {
    return tryWriteValueAsString("INVALID_FORMAT", "invalid format");
  }

  static String couldNotParseJson() {
    return tryWriteValueAsString("BAD_REQUEST", "could not parse JSON");
  }
}
