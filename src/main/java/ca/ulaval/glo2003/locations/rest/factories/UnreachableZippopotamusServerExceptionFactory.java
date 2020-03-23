package ca.ulaval.glo2003.locations.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.errors.ErrorFactory;
import ca.ulaval.glo2003.locations.exceptions.UnreachableZippopotamusServerException;
import org.eclipse.jetty.http.HttpStatus;

public class UnreachableZippopotamusServerExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
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
