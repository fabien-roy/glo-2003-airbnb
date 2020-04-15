package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidCapacityErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidCapacityException.class;
  }

  protected String getError() {
    return "INVALID_CAPACITY";
  }

  protected String getDescription() {
    return "capacity should be a positive number";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
