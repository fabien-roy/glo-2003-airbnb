package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidArrivalDateErrorFactory extends BookingErrorFactory {

  @Override
  public boolean canHandle(BookingException exception) {
    return exception instanceof InvalidArrivalDateException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_ARRIVAL_DATE", "arrival date should be formatted as YYYY-MM-DD");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
