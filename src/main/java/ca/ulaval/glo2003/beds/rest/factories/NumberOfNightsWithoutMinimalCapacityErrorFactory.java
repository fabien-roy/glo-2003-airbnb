package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.NumberOfNightsWithoutMinimalCapacityException;
import org.eclipse.jetty.http.HttpStatus;

public class NumberOfNightsWithoutMinimalCapacityErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return NumberOfNightsWithoutMinimalCapacityException.class;
  }

  protected String getError() {
    return "NUMBER_OF_NIGHTS_WITHOUT_MINIMAL_CAPACITY";
  }

  protected String getDescription() {
    return "a minimal capacity should be provided along with the number of nights";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
