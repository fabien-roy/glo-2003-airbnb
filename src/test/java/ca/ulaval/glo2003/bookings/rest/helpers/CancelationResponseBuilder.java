package ca.ulaval.glo2003.bookings.rest.helpers;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;

public class CancelationResponseBuilder {

  private CancelationResponseBuilder() {}

  public static CancelationResponseBuilder aCancelationResponse() {
    return new CancelationResponseBuilder();
  }

  public CancelationResponse build() {
    return new CancelationResponse();
  }
}
