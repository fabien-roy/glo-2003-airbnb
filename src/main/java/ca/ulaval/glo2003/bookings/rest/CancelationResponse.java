package ca.ulaval.glo2003.bookings.rest;

import ca.ulaval.glo2003.transactions.rest.serializers.PriceSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CancelationResponse {

  @JsonSerialize(using = PriceSerializer.class)
  private double refundAmount;

  public CancelationResponse(double refundAmount) {
    this.refundAmount = refundAmount;
  }

  public double getRefundAmount() {
    return refundAmount;
  }
}
