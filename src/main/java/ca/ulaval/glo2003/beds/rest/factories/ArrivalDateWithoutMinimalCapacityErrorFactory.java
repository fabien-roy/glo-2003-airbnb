package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.ArrivalDateWithoutMinimalCapacityException;
import ca.ulaval.glo2003.beds.exceptions.BedException;
import org.eclipse.jetty.http.HttpStatus;

public class ArrivalDateWithoutMinimalCapacityErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof ArrivalDateWithoutMinimalCapacityException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "ARRIVAL_DATE_WITHOUT_MINIMAL_CAPACITY",
        "a minimal capacity should be provided along with the arrival date");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
