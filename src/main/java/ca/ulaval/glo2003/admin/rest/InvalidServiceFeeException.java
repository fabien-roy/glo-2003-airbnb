package ca.ulaval.glo2003.admin.rest;

public class InvalidServiceFeeException extends RuntimeException {

  public InvalidServiceFeeException() {
    super("INVALID_SERVICE_FEE");
  }
}
