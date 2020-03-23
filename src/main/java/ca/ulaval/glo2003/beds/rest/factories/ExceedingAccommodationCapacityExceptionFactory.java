package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class ExceedingAccommodationCapacityExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
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
