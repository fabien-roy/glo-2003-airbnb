package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;

public class TransactionConverter {

  public TransactionResponse toResponse(Transaction transaction) {
    return new TransactionResponse(
        transaction.getTimestamp().toString(),
        transaction.getFrom(),
        transaction.getTo(),
        transaction.getTotal().toDouble(),
        transaction.getReason().toString());
  }
}
