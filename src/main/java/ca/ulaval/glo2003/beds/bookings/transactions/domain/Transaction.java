package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

  private LocalDate timestamp;
  private String from;
  private String to;
  private BigDecimal total;
  private TransactionReasons reason;

  public Transaction(
      LocalDate timestamp, String from, String to, BigDecimal total, TransactionReasons reason) {
    this.timestamp = timestamp;
    this.from = from;
    this.to = to;
    this.total = total;
    this.reason = reason;
  }

  public LocalDate getTimestamp() {
    return timestamp;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public TransactionReasons getReason() {
    return reason;
  }
}
