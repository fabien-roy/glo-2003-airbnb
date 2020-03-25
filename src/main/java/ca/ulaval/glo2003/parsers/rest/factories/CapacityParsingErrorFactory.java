package ca.ulaval.glo2003.parsers.rest.factories;

import ca.ulaval.glo2003.parsers.exceptions.CapacityProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.eclipse.jetty.http.HttpStatus;

public class CapacityParsingErrorFactory extends ParsingErrorFactory {

  // TODO : This code is duplicated from InvalidCapacityErrorFactory

  @Override
  public boolean canHandle(JsonMappingException exception) {
    return exception instanceof CapacityProcessingException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("INVALID_CAPACITY", "capacity should be a positive number");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
