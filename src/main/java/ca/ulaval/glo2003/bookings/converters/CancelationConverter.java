package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.bookings.rest.CancelationResponse;
import ca.ulaval.glo2003.transactions.converters.PriceConverter;
import ca.ulaval.glo2003.transactions.domain.Price;
import com.google.inject.Inject;

public class CancelationConverter {

  private final PriceConverter priceConverter;

  @Inject
  public CancelationConverter(PriceConverter priceConverter) {
    this.priceConverter = priceConverter;
  }

  public CancelationResponse toResponse(Price refund) {
    Double total = priceConverter.toDouble(refund);

    return new CancelationResponse(total);
  }
}
