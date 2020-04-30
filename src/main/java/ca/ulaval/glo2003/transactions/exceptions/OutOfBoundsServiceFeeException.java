package ca.ulaval.glo2003.transactions.exceptions;

public class OutOfBoundsServiceFeeException extends RuntimeException {

  public OutOfBoundsServiceFeeException() {
    super("OUT_OF_BOUNDS_SERVICE_FEE");
  }
}
