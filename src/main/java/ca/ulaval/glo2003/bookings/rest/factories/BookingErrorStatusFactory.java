package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.*;
import ca.ulaval.glo2003.errors.rest.factories.CatchallErrorStatusFactory;
import org.eclipse.jetty.http.HttpStatus;

public class BookingErrorStatusFactory extends CatchallErrorStatusFactory {

  @Override
  public int create(Exception exception) {
    if (exception instanceof BookingNotFoundException) {
      return HttpStatus.NOT_FOUND_404;
    } else if (exception instanceof InvalidArrivalDateException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidNumberOfNightsException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof InvalidColonySizeException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof ExceedingResidualCapacityException) {
      return HttpStatus.BAD_REQUEST_400;
    } else if (exception instanceof ArrivalDateInThePastException) {
      return HttpStatus.BAD_REQUEST_400;
    } else {
      return defaultStatus();
    }
  }
}
