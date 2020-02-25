package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.Transaction;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.bookings.transactions.rest.TransactionResponse;
import ca.ulaval.glo2003.beds.domain.Price;
import java.time.LocalDate;

public class TransactionMapper {

  public TransactionResponse toResponse(Transaction transaction) {
    TransactionReasons reason = transaction.getReason();
    String from = transaction.getFrom();
    String to = transaction.getTo();
    Price total = transaction.getTotal();
    LocalDate timestamp = transaction.getTimestamp();
    return new TransactionResponse(from, to, total, reason, timestamp);
  }
}
