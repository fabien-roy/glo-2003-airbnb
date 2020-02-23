package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionRequest;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;

public class TransactionMapper {

  public Transaction fromRequest(TransactionRequest request) {
    TransactionReasons reason = TransactionReasons.get(request.getTransactionReasons());
    String from = request.getFrom();
    String to = request.getTo();
    double total = request.getTotal();
    return new Transaction(reason, to, from, total);
  }

  public TransactionResponse toResponse(Transaction transaction) {
    TransactionReasons reason = transaction.getTransactionReasons();
    String from = transaction.getFrom();
    String to = transaction.getTo();
    double total = transaction.getTotal();
    return new TransactionResponse(from, to, total, reason);
  }
}
