package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingAlreadyCanceledException;
import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import org.eclipse.jetty.http.HttpStatus;

public class BookingAlreadyCanceledErrorFactory extends BookingErrorFactory {

  @Override
  public boolean canHandle(BookingException exception) {
    return exception instanceof BookingAlreadyCanceledException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("BOOKING_ALREADY_CANCELED", "booking has already been canceled");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
