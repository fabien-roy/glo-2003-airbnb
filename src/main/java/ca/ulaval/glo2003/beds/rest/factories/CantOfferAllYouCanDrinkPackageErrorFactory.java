package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.AllYouCanDrinkDependencyException;
import org.eclipse.jetty.http.HttpStatus;

public class CantOfferAllYouCanDrinkPackageErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return AllYouCanDrinkDependencyException.class;
  }

  protected String getError() {
    return "CANT_OFFER_ALL_YOU_CAN_DRINK_PACKAGE";
  }

  protected String getDescription() {
    return "in order to offer allYouCanDrink package, you must also offer the bloodthirsty package";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
