package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;

public class TransactionConverter {

  private final TimestampConverter timestampConverter;
  private final PriceConverter priceConverter;

  public TransactionConverter(
      TimestampConverter timestampConverter, PriceConverter priceConverter) {
    this.timestampConverter = timestampConverter;
    this.priceConverter = priceConverter;
  }

  public TransactionResponse toResponse(Transaction transaction) {
    String timestamp = timestampConverter.toString(transaction.getTimestamp());
    Double total = priceConverter.toDouble(transaction.getTotal());

    return new TransactionResponse(
        timestamp,
        transaction.getFrom(),
        transaction.getTo(),
        total,
        transaction.getReason().toString());
  }
}
