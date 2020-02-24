package ca.ulaval.glo2003.beds.rest.exceptions;

public class PackageNotAvailableException extends RuntimeException {

  public PackageNotAvailableException() {
    super("PACKAGE_NOT_AVAILABLE");
  }
}
