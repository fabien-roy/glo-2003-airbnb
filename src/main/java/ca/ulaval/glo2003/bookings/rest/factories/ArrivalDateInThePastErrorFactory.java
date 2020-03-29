package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import org.eclipse.jetty.http.HttpStatus;

public class ArrivalDateInThePastErrorFactory extends BookingErrorFactory {

  @Override
  public boolean canHandle(BookingException exception) {
    return exception instanceof ArrivalDateInThePastException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("ARRIVAL_DATE_IN_THE_PAST", "cannot book a stay in the past");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
