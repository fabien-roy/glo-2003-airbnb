package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.BookingNotAllowedException;
import org.eclipse.jetty.http.HttpStatus;

public class BookingNotAllowedErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof BookingNotAllowedException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("BOOKING_NOT_ALLOWED", "bed owner cannot book its own bed");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
