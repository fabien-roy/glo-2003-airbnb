package ca.ulaval.glo2003.bookings.rest.helpers;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;

import static ca.ulaval.glo2003.transactions.rest.helpers.TransactionResponseObjectMother.createTotal;

public class CancelationResponseBuilder {

  private CancelationResponseBuilder() {}

  private double DEFAULT_TOTAL = createTotal();
  private double total = DEFAULT_TOTAL;

  public static CancelationResponseBuilder aCancelationResponse() {
    return new CancelationResponseBuilder();
  }

  public CancelationResponse build() {
    return new CancelationResponse(total);
  }
}
