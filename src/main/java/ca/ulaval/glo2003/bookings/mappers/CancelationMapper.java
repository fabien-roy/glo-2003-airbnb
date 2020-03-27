package ca.ulaval.glo2003.bookings.mappers;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.domain.Price;

public class CancelationMapper {

  // TODO
  public CancelationResponse toResponse(Price refund) {
    return new CancelationResponse();
  }
}
