package ca.ulaval.glo2003.transactions.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceConverterTest {

  private PriceConverter priceConverter;

  @BeforeEach
  public void setUpMapper() {
    this.priceConverter = new PriceConverter();
  }

  @Test
  public void fromDouble_shouldMapValue() {
    double expectedValue = 100;

    Price price = priceConverter.fromDouble(expectedValue);

    assertEquals(expectedValue, price.getValue().doubleValue());
  }

  @Test
  public void toDouble_withLessThanTwoDecimals_shouldMapWithTwoDecimals() {
    double value = 100.0;
    double expectedValue = 100.00;
    Price total = new Price(BigDecimal.valueOf(value));

    double mappedValue = priceConverter.toDouble(total);

    assertEquals(expectedValue, mappedValue);
  }

  @Test
  public void toDouble_withMoreThanTwoDecimals_shouldMapWithTwoDecimals() {
    double value = 100.000;
    double expectedValue = 100.00;
    Price total = new Price(BigDecimal.valueOf(value));

    double mappedValue = priceConverter.toDouble(total);

    assertEquals(expectedValue, mappedValue);
  }
}
