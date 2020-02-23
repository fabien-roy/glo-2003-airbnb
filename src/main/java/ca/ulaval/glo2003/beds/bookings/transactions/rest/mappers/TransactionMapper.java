package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionRequest;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import java.time.LocalDateTime;

public class TransactionMapper {

  public Transaction fromRequest(TransactionRequest request) {
    TransactionReasons reason = TransactionReasons.get(request.getTransactionReasons());
    String from = request.getFrom();
    String to = request.getTo();
    double total = request.getTotal();
    LocalDateTime timestamp = request.getTimestamp();
    return new Transaction(reason, to, from, total, timestamp);
  }

  public TransactionResponse toResponse(Transaction transaction) {
    TransactionReasons reason = transaction.getTransactionReasons();
    String from = transaction.getFrom();
    String to = transaction.getTo();
    double total = transaction.getTotal();
    LocalDateTime timestamp = transaction.getTimestamp();
    return new TransactionResponse(from, to, total, reason, timestamp);
  }
}
