package ca.ulaval.glo2003.transactions.exceptions;

public class InvalidServiceFeeException extends TransactionException {

  public InvalidServiceFeeException() {
    super("INVALID_SERVICE_FEE");
  }
}
