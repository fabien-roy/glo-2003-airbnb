package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class CancelationRefundCalculator {

  public Price calculateOwnerRefund(Price total) {
    // TODO
    return new Price(BigDecimal.ZERO);
  }

  public Price calculateTenantRefund(Price total) {
    // TODO
    return new Price(BigDecimal.ZERO);
  }
}
