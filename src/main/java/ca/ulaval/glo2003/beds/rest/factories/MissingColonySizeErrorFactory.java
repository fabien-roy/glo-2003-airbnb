package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.MissingColonySizeException;
import org.eclipse.jetty.http.HttpStatus;

public class MissingColonySizeErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return MissingColonySizeException.class;
  }

  protected String getError() {
    return "MISSING_COLONY_SIZE";
  }

  protected String getDescription() {
    return "colony size is mandatory when booking a bed in cohabitation mode";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
