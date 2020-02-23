package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import java.time.LocalDateTime;

public class Transaction {

  private String from;
  private String to;
  private double total;
  private TransactionReasons reason;
  private LocalDateTime timestamp;

  public Transaction(
      TransactionReasons reason, String to, String from, double total, LocalDateTime timestamp) {
    this.reason = reason;
    this.from = from;
    this.to = to;
    this.total = total;
    this.timestamp = timestamp;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public double getTotal() {
    return total;
  }

  public TransactionReasons getTransactionReasons() {
    return reason;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setReason(TransactionReasons reason) {
    this.reason = reason;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
