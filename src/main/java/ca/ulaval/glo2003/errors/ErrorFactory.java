package ca.ulaval.glo2003.errors;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface ErrorFactory {

  static String tryWriteValueAsString(String error, String description) {
    try {
      ErrorResponse response = new ErrorResponse(error, description);
      return new ObjectMapper().writeValueAsString(response);
    } catch (JsonProcessingException ex) {
      return null;
    }
  }

  boolean canHandle(Exception exception);

  String createResponse();

  int createStatus();
}
