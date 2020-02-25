package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;

public class TransactionMapper {

  public TransactionResponse toResponse(Transaction transaction) {
    double total = transaction.getTotal().getValue().doubleValue();
    return new TransactionResponse(
        transaction.getFrom(),
        transaction.getTo(),
        total,
        transaction.getReason(),
        transaction.getTimestamp());
  }
}
