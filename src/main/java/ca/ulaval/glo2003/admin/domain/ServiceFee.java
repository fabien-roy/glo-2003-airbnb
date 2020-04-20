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

  public boolean isSet() {
    return serviceFee != null;
  }

  // TODO : Test ServiceFee.apply()
  public Price apply(Price price) {
    return price.multiply(factorOfPrice());
  }

  private BigDecimal factorOfPrice() {
    return serviceFee.add(BigDecimal.ONE);
  }
}
