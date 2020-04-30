package ca.ulaval.glo2003.transactions.exceptions;

public abstract class TransactionException extends RuntimeException {

  public TransactionException(String message) {
    super(message);
  }
}
