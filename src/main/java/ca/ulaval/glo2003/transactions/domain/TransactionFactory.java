package ca.ulaval.glo2003.transactions.domain;

import ca.ulaval.glo2003.time2.domain.Timestamp;

public class TransactionFactory {

  public Transaction createStayBooked(String from, String to, Price total) {
    return new Transaction(Timestamp.now(), from, to, total, TransactionReasons.STAY_BOOKED);
  }

  public Transaction createStayCompleted(String from, String to, Price total, Timestamp timestamp) {
    return new Transaction(timestamp, from, to, total, TransactionReasons.STAY_COMPLETED);
  }

  public Transaction createStayCanceled(String from, String to, Price total) {
    return new Transaction(Timestamp.now(), from, to, total, TransactionReasons.STAY_CANCELED);
  }

  public Transaction createStayRefunded(String from, String to, Price total, Timestamp timestamp) {
    return new Transaction(timestamp, from, to, total, TransactionReasons.STAY_CANCELED);
  }
}
