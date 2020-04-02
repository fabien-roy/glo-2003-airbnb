package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.ExceedingResidualCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class ExceedingResidualCapacityErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
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
