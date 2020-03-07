package ca.ulaval.glo2003.beds.bookings.transactions.rest;

import ca.ulaval.glo2003.beds.rest.mappers.PriceJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TransactionResponse {

  private String timestamp;
  private String from;
  private String to;

  @JsonSerialize(using = PriceJsonSerializer.class)
  private Double total;

  private String reason;

  public TransactionResponse(
      String timestamp, String from, String to, Double total, String reason) {
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

  public Double getTotal() {
    return total;
  }

  public String getReason() {
    return reason;
  }
}
