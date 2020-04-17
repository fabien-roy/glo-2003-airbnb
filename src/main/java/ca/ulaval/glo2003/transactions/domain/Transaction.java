package ca.ulaval.glo2003.transactions.domain;

import ca.ulaval.glo2003.beds.domain.Bed;
import ca.ulaval.glo2003.bookings.domain.Booking;

public class Transaction {

  // TODO : Transaction should not know about bed and booking (IMPROV)
  // TODO : Always add bed and booking to transaction (CRT)
  private Bed bed;
  private Booking booking;

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

  public Bed getBed() {
    return bed;
  }

  public Booking getBooking() {
    return booking;
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

  public boolean isRefund() {
    return timestamp.isMaxTime();
  }
}
