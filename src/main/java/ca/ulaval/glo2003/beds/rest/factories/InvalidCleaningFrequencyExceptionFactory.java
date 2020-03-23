package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidCleaningFrequencyExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
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
