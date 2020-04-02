package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import org.eclipse.jetty.http.HttpStatus;

public class MissingColonySizeErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof MissingColonySizeException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "MISSING_COLONY_SIZE", "colony size is mandatory when booking a bed in cohabitation mode");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
