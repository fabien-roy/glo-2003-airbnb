package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import org.eclipse.jetty.http.HttpStatus;

public class BookingNotFoundErrorFactory extends BookingErrorFactory {

  String number;

  // TODO : Test this thing
  @Override
  public boolean canHandle(BookingException exception) {
    boolean possibleToHandle = super.canHandle(exception);
    if (possibleToHandle) {
      number = ((BookingNotFoundException) exception).getBookingNumber();
    }
    return possibleToHandle;
  }

  protected Class<?> getAssociatedException() {
    return BookingNotFoundException.class;
  }

  protected String getError() {
    return "BOOKING_NOT_FOUND";
  }

  protected String getDescription() {
    return "booking with number " + number + " could not be found";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
