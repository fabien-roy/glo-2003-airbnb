package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class ExceedingAccommodationCapacityErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof ExceedingAccommodationCapacityException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "EXCEEDING_ACCOMMODATION_CAPACITY",
        "accommodation capacity exceeding maximum viable capacity for the provided bed type");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
