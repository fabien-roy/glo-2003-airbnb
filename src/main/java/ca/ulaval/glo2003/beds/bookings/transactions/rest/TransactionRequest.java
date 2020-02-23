package ca.ulaval.glo2003.beds.bookings.transactions.rest;

public class TransactionRequest {

  private String from;
  private String to;
  private double total;
  private String reason;
  // private DateTime timestamp;

  public TransactionRequest(String from, String to, double total, String reason) {
    this.from = from;
    this.reason = reason;
    this.to = to;
    this.total = total;
    // this.timestamp = timestamp;
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

  public String getTransactionReasons() {
    return reason;
  }
  // public DateTime getTimestamp() { return timestamp; }

  public void setFrom(String from) {
    this.from = from;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }
}
