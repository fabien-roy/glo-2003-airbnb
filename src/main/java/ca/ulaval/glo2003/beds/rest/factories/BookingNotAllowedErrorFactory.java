package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BookingNotAllowedException;
import org.eclipse.jetty.http.HttpStatus;

public class BookingNotAllowedErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return BookingNotAllowedException.class;
  }

  protected String getError() {
    return "BOOKING_NOT_ALLOWED";
  }

  protected String getDescription() {
    return "bed owner cannot book its own bed";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
