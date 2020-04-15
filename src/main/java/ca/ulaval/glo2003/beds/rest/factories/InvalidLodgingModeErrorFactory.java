package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidLodgingModeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidLodgingModeErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidLodgingModeException.class;
  }

  protected String getError() {
    return "INVALID_LODGING_MODE";
  }

  protected String getDescription() {
    return "lodging mode should be one of private or cohabitation";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
