package ca.ulaval.glo2003.beds.rest.factories;

import org.eclipse.jetty.http.HttpStatus;

public class InvalidPublicKeyErrorFactory extends BedErrorFactory {

  protected Class<?> getAssociatedException() {
    return InvalidPublicKeyErrorFactory.class;
  }

  protected String getError() {
    return "INVALID_PUBLIC_KEY";
  }

  protected String getDescription() {
    return "BiteCoins account public key should contain only alphanumeric characters and have a 256-bits length";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
