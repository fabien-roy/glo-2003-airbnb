package ca.ulaval.glo2003.errors.rest.factories;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;

public interface ErrorFactory<E extends Exception> {

  boolean canHandle(E exception);

  ErrorResponse createResponse();

  int createStatus();
}
