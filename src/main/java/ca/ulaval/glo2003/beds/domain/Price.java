package ca.ulaval.glo2003.beds.domain;

import java.math.BigDecimal;
import java.util.Comparator;

public class Price implements Comparable<Price> {

  private BigDecimal value;
  private final Comparator<Price> comparator = Comparator.comparing(Price::getValue);

  public Price(BigDecimal value) {
    this.value = value;
  }

  public BigDecimal getValue() {
    return value;
  }

  public Price multiply(BigDecimal factor) {
    BigDecimal multipliedValue = value.multiply(factor);

    return new Price(multipliedValue);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    Price money = (Price) object;

    // Using compareTo since BigDecimal.equals gives "0.0 != 0.00"
    return value.compareTo(money.getValue()) == 0;
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public int compareTo(Price other) {
    return comparator.compare(this, other);
  }
}
