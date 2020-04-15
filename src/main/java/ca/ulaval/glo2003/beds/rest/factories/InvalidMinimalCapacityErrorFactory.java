package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidMinimalCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidMinimalCapacityErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidMinimalCapacityException.class;
  }

  protected String getError() {
    return "INVALID_MINIMAL_CAPACITY";
  }

  protected String getDescription() {
    return "minimal capacity should be a positive number";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
