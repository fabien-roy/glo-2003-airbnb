package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.domain.Price;

public class CancelationConverter {

  public CancelationResponse toResponse(Price refundAmount) {
    return new CancelationResponse(refundAmount.toDouble());
  }
}
