package ca.ulaval.glo2003.errors.rest.factories;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jetty.http.HttpStatus;

public class JsonParsingErrorFactory extends CatchallErrorFactory {

  protected Class<?> getAssociatedException() {
    return JsonProcessingException.class;
  }

  protected String getError() {
    return "BAD_REQUEST";
  }

  protected String getDescription() {
    return "could not parse JSON";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
