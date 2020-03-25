package ca.ulaval.glo2003.parsers.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;

public class BloodTypesParsingException extends JsonMappingException {

  public BloodTypesParsingException() {
    super("INVALID_BLOOD_TYPES");
  }
}
