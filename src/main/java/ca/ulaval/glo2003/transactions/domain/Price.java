package ca.ulaval.glo2003.transactions.domain;

import ca.ulaval.glo2003.admin.domain.Configuration;
import ca.ulaval.glo2003.admin.domain.ServiceFee;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

public class Price implements Comparable<Price> {

  private BigDecimal bigDecimal;
  private final Comparator<Price> comparator = Comparator.comparing(Price::toBigDecimal);

  public static Price zero() {
    return new Price(BigDecimal.ZERO);
  }

  public static Price valueOf(double price) {
    return new Price(BigDecimal.valueOf(price));
  }

  private Price(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

  public Price getTotal() {
    return add(getServiceFees());
  }

  public Price getServiceFees() {
    ServiceFee serviceFee = Configuration.instance().getServiceFee();
    return serviceFee.getFor(this);
  }

  public Price add(Price price) {
    BigDecimal addedValue = bigDecimal.add(price.toBigDecimal());

    return new Price(addedValue);
  }

  public Price multiply(BigDecimal factor) {
    BigDecimal multipliedValue = bigDecimal.multiply(factor);

    return new Price(multipliedValue);
  }

  public Price divide(BigDecimal divisor, RoundingMode roundingMode) {
    BigDecimal dividedValue = bigDecimal.divide(divisor, roundingMode);

    return new Price(dividedValue);
  }

  public BigDecimal toBigDecimal() {
    return bigDecimal;
  }

  public double toDouble() {
    return bigDecimal.doubleValue();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Price money = (Price) object;

    // Using compareTo since BigDecimal.equals gives "0.0 != 0.00"
    return bigDecimal.compareTo(money.toBigDecimal()) == 0;
  }

  @Override
  public int hashCode() {
    return bigDecimal.hashCode();
  }

  @Override
  public int compareTo(Price other) {
    return comparator.compare(this, other);
  }
}
