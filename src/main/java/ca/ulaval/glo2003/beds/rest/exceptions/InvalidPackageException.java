package ca.ulaval.glo2003.beds.rest.exceptions;

public class InvalidPackageException extends RuntimeException {

  public InvalidPackageException() {
    super("INVALID_PACKAGE");
  }
}
