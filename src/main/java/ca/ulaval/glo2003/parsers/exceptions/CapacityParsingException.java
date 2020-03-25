package ca.ulaval.glo2003.parsers.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;

public class CapacityParsingException extends JsonMappingException {

  public CapacityParsingException() {
    super("INVALID_CAPACITY");
  }
}
