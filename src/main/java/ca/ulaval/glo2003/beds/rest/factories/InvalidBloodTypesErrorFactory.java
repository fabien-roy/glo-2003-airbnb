package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidBloodTypesException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidBloodTypesErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidBloodTypesException.class;
  }

  protected String getError() {
    return "INVALID_BLOOD_TYPES";
  }

  protected String getDescription() {
    return "blood types should be one or many of O-, O+, AB-, AB+, B-, B+, A- or A+";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
