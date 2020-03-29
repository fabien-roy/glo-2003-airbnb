package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.NumberOfNightsWithoutMinimalCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class NumberOfNightsWithoutMinimalCapacityErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof NumberOfNightsWithoutMinimalCapacityException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "NUMBER_OF_NIGHTS_WITHOUT_MINIMAL_CAPACITY",
        "a minimal capacity should be provided along with the number of nights");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
