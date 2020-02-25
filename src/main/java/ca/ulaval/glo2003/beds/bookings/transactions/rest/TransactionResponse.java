package ca.ulaval.glo2003.beds.bookings.transactions.rest;

public class TransactionResponse {

  private String timestamp;
  private String from;
  private String to;
  private double total;
  private String reason;

  public TransactionResponse(
      String timestamp, String from, String to, double total, String reason) {
    this.timestamp = timestamp;
    this.from = from;
    this.to = to;
    this.total = total;
    this.reason = reason;
  }

  public String getTimestamp() {
    return timestamp;
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

  public String getReason() {
    return reason;
  }
}
