package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidCapacityException extends RuntimeException {

  public InvalidCapacityException() {
    super("INVALID_CAPACITY");
  }
}
