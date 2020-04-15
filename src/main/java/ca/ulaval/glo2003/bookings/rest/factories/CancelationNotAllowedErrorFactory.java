package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.CancelationNotAllowedException;
import org.eclipse.jetty.http.HttpStatus;

public class CancelationNotAllowedErrorFactory extends BookingErrorFactory {

  protected Class<?> getAssociatedException() {
    return CancelationNotAllowedException.class;
  }

  protected String getError() {
    return "CANCELATION_NOT_ALLOWED";
  }

  protected String getDescription() {
    return "cancelation period for this booking is over";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
