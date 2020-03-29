package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidCleaningFrequencyErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidCleaningFrequencyException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_CLEANING_FREQUENCY",
        "cleaning frequency should be one of weekly, monthly, annual or never");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
