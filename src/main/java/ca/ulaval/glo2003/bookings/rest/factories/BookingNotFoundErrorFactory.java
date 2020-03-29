package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import org.eclipse.jetty.http.HttpStatus;

public class BookingNotFoundErrorFactory extends BookingErrorFactory {

  String number;

  @Override
  public boolean canHandle(BookingException exception) {
    boolean possibleToHandle = exception instanceof BookingNotFoundException;
    if (possibleToHandle) {
      number = ((BookingNotFoundException) exception).getBookingNumber();
    }
    return possibleToHandle;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "BOOKING_NOT_FOUND", "booking with number " + number + " could not be found");
  }

  @Override
  public int createStatus() {
    return HttpStatus.NOT_FOUND_404;
  }
}
