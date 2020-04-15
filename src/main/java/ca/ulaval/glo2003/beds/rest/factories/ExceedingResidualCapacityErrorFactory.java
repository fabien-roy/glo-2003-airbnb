package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.ExceedingResidualCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class ExceedingResidualCapacityErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return ExceedingResidualCapacityException.class;
  }

  protected String getError() {
    return "EXCEEDING_RESIDUAL_CAPACITY";
  }

  protected String getDescription() {
    return "colony size is exceeding residual bed capacity";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
