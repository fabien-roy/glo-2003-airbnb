package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidDistanceWithoutOriginException extends RuntimeException {

  public InvalidDistanceWithoutOriginException() {
    super("MAX_DISTANCE_WITHOUT_ORIGIN");
  }
}
