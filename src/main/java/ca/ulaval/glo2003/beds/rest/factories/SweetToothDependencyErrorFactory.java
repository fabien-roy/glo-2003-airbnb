package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;
import org.eclipse.jetty.http.HttpStatus;

public class SweetToothDependencyErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof SweetToothDependencyException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "CANT_OFFER_SWEET_TOOTH_PACKAGE",
        "in order to offer sweetTooth package, you must also offer the bloodthirsty and allYouCanDrink packages");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
