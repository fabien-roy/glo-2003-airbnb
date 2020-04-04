package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CancelationRefundCalculator {

  public BigDecimal calculateOwnerRefund(Price total) {
    return total.getValue().divide(BigDecimal.valueOf(2), RoundingMode.UP);
  }

  public BigDecimal calculateTenantRefund(Price total) {
    return total.getValue().divide(BigDecimal.valueOf(2), RoundingMode.DOWN);
  }
}
