package ca.ulaval.glo2003.locations.exceptions;

public abstract class LocationException extends RuntimeException {

  public LocationException(String message) {
    super(message);
  }
}
