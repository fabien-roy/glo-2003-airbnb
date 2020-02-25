package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import java.util.Date;

public class TransactionFactory {

  public Transaction create(Transaction transaction) {
    Date timestamp = new Date();
    transaction.setTimestamp(timestamp);
    return transaction;
  }
}
