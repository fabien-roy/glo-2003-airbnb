package ca.ulaval.glo2003.transactions.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PriceTest {

  @Test
  void multiply_shouldMultiplyValue() {
    Price price = new Price(BigDecimal.valueOf(100));
    BigDecimal expectedMultipliedValue = BigDecimal.valueOf(500);

    price = price.multiply(BigDecimal.valueOf(5));

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
    Price price = new Price(BigDecimal.ZERO);
    Price otherPrice = new Price(BigDecimal.ONE);

    boolean result = price.equals(otherPrice);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    Price price = new Price(BigDecimal.ZERO);
    Price otherPrice = new Price(BigDecimal.ZERO);

    boolean result = price.equals(otherPrice);

    assertTrue(result);
  }
}
