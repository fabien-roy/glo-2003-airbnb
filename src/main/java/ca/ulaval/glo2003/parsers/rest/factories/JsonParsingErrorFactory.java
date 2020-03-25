package ca.ulaval.glo2003.parsers.rest.factories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.eclipse.jetty.http.HttpStatus;

public class JsonParsingErrorFactory extends ParsingErrorFactory {

  @Override
  public boolean canHandle(JsonMappingException exception) {
    return exception instanceof JsonProcessingException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("BAD_REQUEST", "could not parse JSON");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
