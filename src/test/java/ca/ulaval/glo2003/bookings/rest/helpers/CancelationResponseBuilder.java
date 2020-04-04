package ca.ulaval.glo2003.bookings.rest.helpers;

import static ca.ulaval.glo2003.bookings.rest.helpers.CancelationResponseObjectMother.createRefundAmount;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;

public class CancelationResponseBuilder {

  private CancelationResponseBuilder() {}

  private double DEFAULT_REFUND_AMOUNT = createRefundAmount();
  private double refundAmount = DEFAULT_REFUND_AMOUNT;

  public static CancelationResponseBuilder aCancelationResponse() {
    return new CancelationResponseBuilder();
  }

  public CancelationResponse build() {
    return new CancelationResponse(refundAmount);
  }
}
