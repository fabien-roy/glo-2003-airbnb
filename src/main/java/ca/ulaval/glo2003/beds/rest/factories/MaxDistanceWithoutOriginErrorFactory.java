package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.MaxDistanceWithoutOriginException;
import org.eclipse.jetty.http.HttpStatus;

public class MaxDistanceWithoutOriginErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return MaxDistanceWithoutOriginException.class;
  }

  protected String getError() {
    return "MAX_DISTANCE_WITHOUT_ORIGIN";
  }

  protected String getDescription() {
    return "an origin point should be provided along with the maximum distance";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
