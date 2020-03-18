package ca.ulaval.glo2003.beds.exceptions;

public abstract class BedException extends RuntimeException {

  public BedException(String message) {
    super(message);
  }
}
