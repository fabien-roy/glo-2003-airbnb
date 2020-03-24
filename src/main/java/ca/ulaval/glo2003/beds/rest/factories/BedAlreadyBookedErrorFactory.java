package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedAlreadyBookedException;
import ca.ulaval.glo2003.beds.exceptions.BedException;
import org.eclipse.jetty.http.HttpStatus;

public class BedAlreadyBookedErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof BedAlreadyBookedException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("BED_ALREADY_BOOKED", "bed is already booked for selected dates");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
