package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidDistanceException extends RuntimeException {

  public InvalidDistanceException() {
    super("INVALID_MAX_DISTANCE");
  }
}
