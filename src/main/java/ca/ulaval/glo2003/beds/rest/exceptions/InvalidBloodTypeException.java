package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidBloodTypeException extends RuntimeException {

  public InvalidBloodTypeException() {
    super("INVALID_BLOOD_TYPE");
  }
}
