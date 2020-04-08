package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import javax.inject.Inject;

public class TransactionConverter {

  private final TimestampConverter timestampConverter;

  @Inject
  public TransactionConverter(TimestampConverter timestampConverter) {
    this.timestampConverter = timestampConverter;
  }

  public TransactionResponse toResponse(Transaction transaction) {
    String timestamp = timestampConverter.toString(transaction.getTimestamp());

    return new TransactionResponse(
        timestamp,
        transaction.getFrom(),
        transaction.getTo(),
        transaction.getTotal().toDouble(),
        transaction.getReason().toString());
  }
}
