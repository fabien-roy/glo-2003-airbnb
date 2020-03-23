package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.BedNotFoundException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class BedNotFoundExceptionFactory implements ErrorFactory {

  String number;

  @Override
  public boolean canHandle(Exception exception) {
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
