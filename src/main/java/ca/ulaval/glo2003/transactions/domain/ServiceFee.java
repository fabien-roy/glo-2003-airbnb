package ca.ulaval.glo2003.transactions.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    return serviceFee.divide(BigDecimal.valueOf(100), RoundingMode.UP);
  }
}
