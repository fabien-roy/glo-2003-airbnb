package ca.ulaval.glo2003.transactions.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceTest {

  private static Price price;

  @BeforeEach
  public void setUpPrice() {
    price = new Price(BigDecimal.valueOf(100));
  }

  @Test
  void multiply_shouldMultiplyValue() {
    BigDecimal expectedValue = BigDecimal.valueOf(500);

    price = price.multiply(BigDecimal.valueOf(5));

    assertEquals(expectedValue, price.getValue());
  }

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotPrice() {
    Object object = new Object();

    boolean result = price.equals(object);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    Price otherPrice = new Price(BigDecimal.ZERO);

    boolean result = price.equals(otherPrice);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    Price otherPrice = new Price(price.getValue());

    boolean result = price.equals(otherPrice);

    assertTrue(result);
  }
}
