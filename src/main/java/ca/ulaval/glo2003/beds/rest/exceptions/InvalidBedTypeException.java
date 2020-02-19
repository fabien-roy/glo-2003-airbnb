package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidBedTypeException extends RuntimeException {

  public InvalidBedTypeException() {
    super("INVALID_BED_TYPE");
  }
}
