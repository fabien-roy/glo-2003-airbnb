package ca.ulaval.glo2003.admin.domain;

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
}
