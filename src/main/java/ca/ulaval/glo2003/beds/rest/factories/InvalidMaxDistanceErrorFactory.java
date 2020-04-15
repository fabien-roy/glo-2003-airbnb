package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidMaxDistanceException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidMaxDistanceErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidMaxDistanceException.class;
  }

  protected String getError() {
    return "INVALID_MAX_DISTANCE";
  }

  protected String getDescription() {
    return "distance should be a number greater than 0";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
