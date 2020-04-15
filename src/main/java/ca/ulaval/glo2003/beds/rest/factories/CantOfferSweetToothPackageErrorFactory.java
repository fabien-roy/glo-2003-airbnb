package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.SweetToothDependencyException;
import org.eclipse.jetty.http.HttpStatus;

public class CantOfferSweetToothPackageErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return SweetToothDependencyException.class;
  }

  protected String getError() {
    return "CANT_OFFER_SWEET_TOOTH_PACKAGE";
  }

  protected String getDescription() {
    return "in order to offer sweetTooth package, you must also offer the bloodthirsty and allYouCanDrink packages";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
