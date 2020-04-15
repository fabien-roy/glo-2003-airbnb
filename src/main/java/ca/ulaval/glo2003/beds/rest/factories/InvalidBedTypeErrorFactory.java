package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidBedTypeErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidBedTypeException.class;
  }

  protected String getError() {
    return "INVALID_BED_TYPE";
  }

  protected String getDescription() {
    return "bed type should be one of latex, memoryFoam or springs";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
