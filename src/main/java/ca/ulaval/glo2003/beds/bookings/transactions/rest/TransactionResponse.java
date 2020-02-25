package ca.ulaval.glo2003.beds.bookings.transactions.rest;

import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;
import ca.ulaval.glo2003.beds.domain.Price;
import java.time.LocalDateTime;

public class TransactionResponse {

  private String from;
  private String to;
  private Price total;
  private TransactionReasons reason;
  private LocalDateTime timestamp;

  public TransactionResponse(
      String from, String to, Price total, TransactionReasons reason, LocalDateTime timestamp) {
    this.from = from;
    this.reason = reason;
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

  public Price getTotal() {
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

  public void setTotal(Price total) {
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
