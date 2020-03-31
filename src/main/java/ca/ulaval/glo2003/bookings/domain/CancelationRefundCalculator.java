package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CancelationRefundCalculator {

  public Price calculateOwnerRefund(Price total) {
    Price halfOwnerPrice =
        new Price(total.getValue().divide(BigDecimal.valueOf(2), RoundingMode.UP));
    return halfOwnerPrice;
  }

  public Price calculateTenantRefund(Price total) {
    Price halfTenantPrice =
        new Price(total.getValue().divide(BigDecimal.valueOf(2), RoundingMode.DOWN));
    return halfTenantPrice;
  }
}
