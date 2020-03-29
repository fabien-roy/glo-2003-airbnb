package ca.ulaval.glo2003.errors.rest.factories;

public interface ErrorFactory<E extends Exception> {

  boolean canHandle(E exception);

  String createResponse();

  int createStatus();
}
