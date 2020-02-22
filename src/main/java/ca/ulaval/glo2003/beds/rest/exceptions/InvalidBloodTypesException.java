package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidBloodTypesException extends RuntimeException {

  public InvalidBloodTypesException() {
    super("INVALID_BLOOD_TYPES");
  }
}
