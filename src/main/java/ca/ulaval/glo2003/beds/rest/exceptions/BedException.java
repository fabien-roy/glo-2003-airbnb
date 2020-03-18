package ca.ulaval.glo2003.beds.rest.exceptions;

import ca.ulaval.glo2003.interfaces.rest.exceptions.AirbnbException;

public abstract class BedException extends AirbnbException {

  public BedException(String message) {
    super(message);
  }
}
