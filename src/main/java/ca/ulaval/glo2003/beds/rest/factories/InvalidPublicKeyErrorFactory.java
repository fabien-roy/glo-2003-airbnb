package ca.ulaval.glo2003.beds.rest.factories;

import ca.ulaval.glo2003.beds.exceptions.BedException;
import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidPublicKeyErrorFactory extends BedErrorFactory {

  @Override
  public boolean canHandle(BedException exception) {
    return exception instanceof InvalidPublicKeyException;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString(
        "INVALID_PUBLIC_KEY",
        "BiteCoins account public key should contain only alphanumeric characters and have a 256-bits length");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
