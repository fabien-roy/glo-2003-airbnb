package ca.ulaval.glo2003.beds.bookings.transactions.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

  private Date timestamp;
  private String from;
  private String to;
  private BigDecimal total;
  private TransactionReasons reason;

  public Transaction(String from, String to, BigDecimal total, TransactionReasons reason) {
    this.from = from;
    this.to = to;
    this.total = total;
    this.reason = reason;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}
