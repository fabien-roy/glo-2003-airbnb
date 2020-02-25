package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import ca.ulaval.glo2003.beds.domain.Price;
import java.time.LocalDate;

public class Transaction {

  private LocalDate timestamp;
  private String from;
  private String to;
  private Price total;
  private TransactionReasons reason;

  public Transaction(
      LocalDate timestamp, String from, String to, Price total, TransactionReasons reason) {
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

  public Price getTotal() {
    return total;
  }

  public TransactionReasons getReason() {
    return reason;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public void setTo(String to) {
    this.to = to;
  }
}
