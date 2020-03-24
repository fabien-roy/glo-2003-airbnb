package ca.ulaval.glo2003.locations.rest.factories;

import ca.ulaval.glo2003.locations.exceptions.LocationServiceException;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import org.eclipse.jetty.http.HttpStatus;

public class UnreachableZippopotamusServerErrorFactory extends LocationServiceErrorFactory {

  @Override
  public boolean canHandle(LocationServiceException exception) {
    return exception instanceof UnreachableZippopotamusServerException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "UNREACHABLE_ZIPPOPOTAMUS_SERVER", "zippopotamus server unreachable");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
