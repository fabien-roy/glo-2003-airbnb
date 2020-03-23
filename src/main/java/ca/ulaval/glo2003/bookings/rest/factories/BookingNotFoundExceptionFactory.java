package ca.ulaval.glo2003.bookings.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class BookingNotFoundExceptionFactory implements ErrorFactory {

  String number;

  @Override
  public boolean canHandle(Exception exception) {
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
