package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidBedTypeException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidBedTypeErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidBedTypeException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_BED_TYPE", "bed type should be one of latex, memoryFoam or springs");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
