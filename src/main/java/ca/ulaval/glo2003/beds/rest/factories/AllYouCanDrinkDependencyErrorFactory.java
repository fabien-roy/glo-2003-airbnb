package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;
import ca.ulaval.glo2003.beds.exceptions.BedException;
import org.eclipse.jetty.http.HttpStatus;

public class AllYouCanDrinkDependencyErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof AllYouCanDrinkDependencyException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "CANT_OFFER_ALL_YOU_CAN_DRINK_PACKAGE",
        "in order to offer allYouCanDrink package, you must also offer the bloodthirsty package");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
