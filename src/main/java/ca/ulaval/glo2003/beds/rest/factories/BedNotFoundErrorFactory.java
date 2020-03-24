package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import org.eclipse.jetty.http.HttpStatus;

public class BedNotFoundErrorFactory extends BedErrorFactory {

  String number;

  @Override
  public boolean canHandle(BedException exception) {
    boolean possibleToHandle = exception instanceof BedNotFoundException;
    if (possibleToHandle) {
      number = ((BedNotFoundException) exception).getBedNumber();
    }
    return possibleToHandle;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "BED_NOT_FOUND", "bed with number " + number + " could not be found");
  }

  @Override
  public int createStatus() {
    return HttpStatus.NOT_FOUND_404;
  }
}
