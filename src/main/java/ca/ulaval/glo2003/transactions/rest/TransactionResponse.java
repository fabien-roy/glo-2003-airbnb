package ca.ulaval.glo2003.transactions.rest;

import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TransactionResponse {

  private String timestamp;
  private String from;
  private String to;

  @JsonSerialize(using = PriceSerializer.class)
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
