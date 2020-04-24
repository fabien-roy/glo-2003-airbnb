package ca.ulaval.glo2003.transactions.domain;

import ca.ulaval.glo2003.time2.domain.Timestamp;

public class Transaction {

  private Timestamp timestamp;
  private String from;
  private String to;
  private Price total;
  private TransactionReasons reason;

  public Transaction(
      Timestamp timestamp, String from, String to, Price total, TransactionReasons reason) {
    this.timestamp = timestamp;
    this.from = from;
    this.to = to;
    this.total = total;
    this.reason = reason;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public Price getTotal() {
    return total;
  }

  public TransactionReasons getReason() {
    return reason;
  }
}
