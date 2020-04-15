package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.ArrivalDateWithoutMinimalCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class ArrivalDateWithoutMinimalCapacityErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return ArrivalDateWithoutMinimalCapacityException.class;
  }

  protected String getError() {
    return "ARRIVAL_DATE_WITHOUT_MINIMAL_CAPACITY";
  }

  protected String getDescription() {
    return "a minimal capacity should be provided along with the arrival date";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
