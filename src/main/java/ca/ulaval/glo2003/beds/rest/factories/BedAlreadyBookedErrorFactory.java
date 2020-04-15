package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import org.eclipse.jetty.http.HttpStatus;

public class BedAlreadyBookedErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return BedAlreadyBookedException.class;
  }

  protected String getError() {
    return "BED_ALREADY_BOOKED";
  }

  protected String getDescription() {
    return "bed is already booked for selected dates";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
