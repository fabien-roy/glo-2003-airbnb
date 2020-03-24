package ca.ulaval.glo2003.bookings.rest.factories;

import ca.ulaval.glo2003.bookings.exceptions.BookingException;
import ca.ulaval.glo2003.bookings.exceptions.ExceedingResidualCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class ExceedingResidualCapacityErrorFactory extends BookingErrorFactory {

  @Override
  public boolean canHandle(BookingException exception) {
    return exception instanceof ExceedingResidualCapacityException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "EXCEEDING_RESIDUAL_CAPACITY", "colony size is exceeding residual bed capacity");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
