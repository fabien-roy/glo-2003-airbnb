package ca.ulaval.glo2003.errors.rest.factories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jetty.http.HttpStatus;

public class JsonParsingErrorFactory extends CatchallErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
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
