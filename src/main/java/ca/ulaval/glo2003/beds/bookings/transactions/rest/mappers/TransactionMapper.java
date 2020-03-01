package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import java.time.format.DateTimeFormatter;

public class TransactionMapper {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  public TransactionResponse toResponse(Transaction transaction) {
    String timestamp = transaction.getTimestamp().format(DATE_TIME_FORMATTER) + "Z";
    double total = transaction.getTotal().getValue().doubleValue();
    return new TransactionResponse(
        timestamp,
        transaction.getFrom(),
        transaction.getTo(),
        total,
        transaction.getReason().toString());
  }
}
