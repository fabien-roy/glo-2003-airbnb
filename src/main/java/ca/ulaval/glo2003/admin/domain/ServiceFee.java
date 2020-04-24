package ca.ulaval.glo2003.admin.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class ServiceFee {

  private final BigDecimal serviceFee;

  public ServiceFee(BigDecimal serviceFee) {
    this.serviceFee = serviceFee;
  }

  public BigDecimal toBigDecimal() {
    return serviceFee;
  }

  public Price getFor(Price price) {
    return serviceFee == null ? Price.zero() : price.multiply(factorOfPrice());
  }

  private BigDecimal factorOfPrice() {
    return serviceFee.divide(BigDecimal.valueOf(100));
  }
}
