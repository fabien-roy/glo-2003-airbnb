package ca.ulaval.glo2003.interfaces.exceptions;

public abstract class ExternalServiceException extends RuntimeException {

  public ExternalServiceException(String message) {
    super(message);
  }
}
