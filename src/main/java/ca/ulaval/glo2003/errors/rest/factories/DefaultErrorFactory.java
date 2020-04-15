package ca.ulaval.glo2003.errors.rest.factories;

import org.eclipse.jetty.http.HttpStatus;

public class DefaultErrorFactory extends AbstractErrorFactory<Exception> {

  protected Class<?> getAssociatedException() {
    return Exception.class;
  }

  protected String getError() {
    return "BAD_REQUEST";
  }

  protected String getDescription() {
    return "something went wrong";
  }

  protected int getStatus() {
    return HttpStatus.BAD_REQUEST_400;
  }
}
