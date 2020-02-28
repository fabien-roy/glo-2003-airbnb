package ca.ulaval.glo2003.beds.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PriceTest {

  @Test
  void multiply_shouldMultiplyValue() {
    BigDecimal value = BigDecimal.valueOf(100);
    BigDecimal factor = BigDecimal.valueOf(5);
    Price price = new Price(value);
    BigDecimal expectedMultipliedValue = BigDecimal.valueOf(500);

    price = price.multiply(factor);

    assertEquals(expectedMultipliedValue, price.getValue());
  }

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotPrice() {
    Price price = new Price(BigDecimal.ZERO);
    Object object = new Object();

    boolean result = price.equals(object);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    BigDecimal value = BigDecimal.ZERO;
    BigDecimal otherValue = BigDecimal.ONE;
    Price price = new Price(value);
    Price otherPrice = new Price(otherValue);

    boolean result = price.equals(otherPrice);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    BigDecimal value = BigDecimal.ZERO;
    Price price = new Price(value);
    Price otherPrice = new Price(value);

    boolean result = price.equals(otherPrice);

    assertTrue(result);
  }
}
