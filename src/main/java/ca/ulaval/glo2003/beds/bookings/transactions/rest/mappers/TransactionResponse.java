package ca.ulaval.glo2003.beds.bookings.transactions.rest.mappers;

// import ca.ulaval.glo2003.beds.rest.PackageResponse;
import ca.ulaval.glo2003.beds.bookings.transactions.domain.TransactionReasons;

public class TransactionResponse {

  private String from;
  private String to;
  private double total;
  private TransactionReasons reason;
  // private DateTime timestamp;

  public TransactionResponse(String from, String to, double total, TransactionReasons reason) {
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

  public TransactionReasons getTransactionReasons() {
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

  public void setReason(TransactionReasons reason) {
    this.reason = reason;
  }
}
