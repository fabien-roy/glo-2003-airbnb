package ca.ulaval.glo2003.transactions.domain;

import java.time.LocalDateTime;

public class Transaction {

  private LocalDateTime timestamp;
  private String from;
  private String to;
  private Price total;
  private TransactionReasons reason;

  public Transaction(
      LocalDateTime timestamp, String from, String to, Price total, TransactionReasons reason) {
    this.timestamp = timestamp;
    this.from = from;
    this.to = to;
    this.total = total;
    this.reason = reason;
  }

  public LocalDateTime getTimestamp() {
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
