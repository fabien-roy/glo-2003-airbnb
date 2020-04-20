package ca.ulaval.glo2003.admin.domain;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;

public class ServiceFee {

  private final BigDecimal serviceFee;

  public ServiceFee(BigDecimal serviceFee) {
    this.serviceFee = serviceFee;
  }

  public Price apply(Price price) {
    return serviceFee == null ? price : price.multiply(factorOfPrice());
  }

  private BigDecimal factorOfPrice() {
    return serviceFee.divide(BigDecimal.valueOf(100)).add(BigDecimal.ONE);
  }
}
