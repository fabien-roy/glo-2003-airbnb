package ca.ulaval.glo2003.transactions.converters;

import ca.ulaval.glo2003.transactions.domain.Transaction;
import ca.ulaval.glo2003.transactions.rest.TransactionResponse;
import java.time.format.DateTimeFormatter;
import javax.inject.Inject;

public class TransactionConverter {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  private final PriceConverter priceConverter;

  @Inject
  public TransactionConverter(PriceConverter priceConverter) {
    this.priceConverter = priceConverter;
  }

  public TransactionResponse toResponse(Transaction transaction) {
    String timestamp = transaction.getTimestamp().format(DATE_TIME_FORMATTER) + "Z";
    Double total = priceConverter.toDouble(transaction.getTotal());
    return new TransactionResponse(
        timestamp,
        transaction.getFrom(),
        transaction.getTo(),
        total,
        transaction.getReason().toString());
  }
}
