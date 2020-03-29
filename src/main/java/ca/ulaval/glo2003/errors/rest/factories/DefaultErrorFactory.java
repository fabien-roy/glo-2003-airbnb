package ca.ulaval.glo2003.errors.rest.factories;

import org.eclipse.jetty.http.HttpStatus;

public class DefaultErrorFactory extends AbstractErrorFactory<Exception> {

  @Override
  public boolean canHandle(Exception exception) {
    return true;
  }

  @Override
  public String createResponse() {
    return tryWriteValueAsString("BAD_REQUEST", "something went wrong");
  }

  @Override
  public int createStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
