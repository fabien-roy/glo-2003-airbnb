package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.ExceedingAccommodationCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class ExceedingAccommodationCapacityErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return ExceedingAccommodationCapacityException.class;
  }

  protected String getError() {
    return "EXCEEDING_ACCOMMODATION_CAPACITY";
  }

  protected String getDescription() {
    return "accommodation capacity exceeding maximum viable capacity for the provided bed type";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
