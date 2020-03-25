package ca.ulaval.glo2003.parsers.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;

public class ColonySizeParsingException extends JsonMappingException {

  public ColonySizeParsingException() {
    super("INVALID_COLONY_SIZE");
  }
}
