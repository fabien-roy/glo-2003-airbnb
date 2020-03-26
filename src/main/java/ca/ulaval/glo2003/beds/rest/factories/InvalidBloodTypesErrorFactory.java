package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidBloodTypesErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidBloodTypesException;
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
