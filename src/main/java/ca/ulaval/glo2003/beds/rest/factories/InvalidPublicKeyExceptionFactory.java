package ca.ulaval.glo2003.beds.rest.factories;

import static ca.ulaval.glo2003.errors.ErrorFactory.tryWriteValueAsString;

import ca.ulaval.glo2003.beds.exceptions.InvalidPublicKeyException;
import ca.ulaval.glo2003.errors.ErrorFactory;
import org.eclipse.jetty.http.HttpStatus;

public class InvalidPublicKeyExceptionFactory implements ErrorFactory {

  @Override
  public boolean canHandle(Exception exception) {
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
