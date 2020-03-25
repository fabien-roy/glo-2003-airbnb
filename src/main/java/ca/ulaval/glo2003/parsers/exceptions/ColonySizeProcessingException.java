package ca.ulaval.glo2003.parsers.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;

public class ColonySizeProcessingException extends JsonMappingException {

  public ColonySizeProcessingException() {
    super("INVALID_COLONY_SIZE");
  }
}
