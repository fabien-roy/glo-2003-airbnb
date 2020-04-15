package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.InvalidCleaningFrequencyException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidCleaningFrequencyErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidCleaningFrequencyException.class;
  }

  protected String getError() {
    return "INVALID_CLEANING_FREQUENCY";
  }

  protected String getDescription() {
    return "cleaning frequency should be one of weekly, monthly, annual or never";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
