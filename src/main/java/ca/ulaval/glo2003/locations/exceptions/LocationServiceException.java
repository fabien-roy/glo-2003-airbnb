package ca.ulaval.glo2003.locations.exceptions;

public abstract class LocationServiceException extends RuntimeException {

  public LocationServiceException(String message) {
    super(message);
  }
}
