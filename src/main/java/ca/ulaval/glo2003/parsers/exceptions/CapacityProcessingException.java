package ca.ulaval.glo2003.parsers.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;

public class CapacityProcessingException extends JsonMappingException {

  public CapacityProcessingException() {
    super("INVALID_CAPACITY");
  }
}
