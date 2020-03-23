package ca.ulaval.glo2003.bookings.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.bookings.exceptions.ExceedingResidualCapacityException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class ExceedingResidualCapacityExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
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
