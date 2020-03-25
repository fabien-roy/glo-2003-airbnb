package ca.ulaval.glo2003.parsers.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;

public class NumberOfNightsParsingException extends JsonMappingException {

  public NumberOfNightsParsingException() {
    super("INVALID_NUMBER_OF_NIGHTS");
  }
}
