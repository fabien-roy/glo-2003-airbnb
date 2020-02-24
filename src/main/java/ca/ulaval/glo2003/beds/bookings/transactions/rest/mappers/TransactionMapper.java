package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import java.time.LocalDateTime;

public class TransactionMapper {

  public TransactionResponse toResponse(Transaction transaction) {
    TransactionReasons reason = transaction.getTransactionReasons();
    String from = transaction.getFrom();
    String to = transaction.getTo();
    double total = transaction.getTotal();
    LocalDateTime timestamp = transaction.getTimestamp();
    return new TransactionResponse(from, to, total, reason, timestamp);
  }
}
