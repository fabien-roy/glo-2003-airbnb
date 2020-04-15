package ca.ulaval.glo2003.errors.rest.factories;

import ca.ulaval.glo2003.errors.rest.ErrorResponse;

public abstract class AbstractErrorFactory<E extends Exception> implements ErrorFactory<E> {

  @Override
  public boolean canHandle(E exception) {
    return getAssociatedException().isInstance(exception);
  }

  @Override
  public ErrorResponse createResponse() {
    ErrorResponse response = new ErrorResponse();
    response.setError(getError());
    response.setDescription(getDescription());
    return response;
  }

  @Override
  public int createStatus() {
    return getStatus();
  }

  protected abstract Class<?> getAssociatedException();

  protected abstract String getError();

  protected abstract String getDescription();

  protected abstract int getStatus();
}
