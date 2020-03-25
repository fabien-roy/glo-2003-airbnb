package ca.ulaval.glo2003.parsers.rest.factories;

import ca.ulaval.glo2003.parsers.exceptions.ColonySizeParsingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.eclipse.jetty.http.HttpStatus;

public class ColonySizeParsingErrorFactory extends ParsingErrorFactory {

  // TODO : This code is duplicated from InvalidColonySizeErrorFactory

  @Override
  public boolean canHandle(JsonMappingException exception) {
    return exception instanceof ColonySizeParsingException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("INVALID_COLONY_SIZE", "colony size should be a positive number");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
