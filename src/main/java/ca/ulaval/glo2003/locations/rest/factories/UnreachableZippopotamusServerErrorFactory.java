package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import org.eclipse.jetty.http.HttpStatus;

public class UnreachableZippopotamusServerErrorFactory extends LocationErrorFactory {

  protected Class<?> getAssociatedException() {
    return UnreachableZippopotamusServerException.class;
  }

  protected String getError() {
    return "UNREACHABLE_ZIPPOPOTAMUS_SERVER";
  }

  protected String getDescription() {
    return "zippopotamus server unreachable";
  }

  protected int getStatus() {
    return HttpStatus.SERVICE_UNAVAILABLE_503;
  }
}
