package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import org.eclipse.jetty.http.HttpStatus;

public class BedNotFoundErrorFactory extends BedErrorFactory {

  String number;

  // TODO : Test this thing
  @Override
  public boolean canHandle(BedException exception) {
    boolean possibleToHandle = super.canHandle(exception);
    if (possibleToHandle) {
      number = ((BedNotFoundException) exception).getBedNumber();
    }
    return possibleToHandle;
  }

  protected Class<?> getAssociatedException() {
    return BedNotFoundException.class;
  }

  protected String getError() {
    return "BED_NOT_FOUND";
  }

  protected String getDescription() {
    return "bed with number " + number + " could not be found";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
