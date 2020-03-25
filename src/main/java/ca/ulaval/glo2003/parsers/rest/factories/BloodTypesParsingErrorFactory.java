package ca.ulaval.glo2003.parsers.rest.factories;

import ca.ulaval.glo2003.parsers.exceptions.BloodTypesParsingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.eclipse.jetty.http.HttpStatus;

public class BloodTypesParsingErrorFactory extends ParsingErrorFactory {

  // TODO : This code is duplicated from InvalidBloodTypesErrorFactory

  @Override
  public boolean canHandle(JsonMappingException exception) {
    return exception instanceof BloodTypesParsingException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_BLOOD_TYPES",
        "blood types should be one or many of O-, O+, AB-, AB+, B-, B+, A- or A+");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
