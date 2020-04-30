package ca.ulaval.glo2003.transactions.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.admin.domain.Configuration;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PriceTest {

  private static Price price;
  private static ServiceFee serviceFee = mock(ServiceFee.class);
  private static Price serviceFeePrice = Price.valueOf(15);

  @BeforeEach
  public void setUpPrice() {
    price = Price.valueOf(100);
    setUpServiceFee(price, serviceFeePrice);
  }

  private void setUpServiceFee(Price price, Price serviceFeePrice) {
    when(serviceFee.getFor(price)).thenReturn(serviceFeePrice);
    Configuration.instance().setServiceFee(serviceFee);
  }

  @Test
  public void zero_shouldReturnPriceWithZeroValue() {
    Price zeroPrice = Price.valueOf(0);

    price = Price.zero();

    assertEquals(zeroPrice, price);
  }

  @ParameterizedTest
  @MethodSource("provideServiceFees")
  public void getTotal_shouldGetValuePlusServiceFee(
      Price price, Price serviceFeePrice, Price total) {
    setUpServiceFee(price, serviceFeePrice);

    Price actualTotal = price.getTotal();

    assertEquals(total, actualTotal);
  }

  private static Stream<Arguments> provideServiceFees() {
    return Stream.of(
        Arguments.of(Price.valueOf(100), Price.valueOf(15), Price.valueOf(115)),
        Arguments.of(Price.valueOf(0), Price.valueOf(0), Price.valueOf(0)),
        Arguments.of(Price.valueOf(100), Price.valueOf(0), Price.valueOf(100)),
        Arguments.of(Price.valueOf(55.55), Price.valueOf(.24), Price.valueOf(55.79)));
  }

  @Test
  public void getServiceFee_shouldGetServiceFee() {
    price = price.getServiceFees();

    assertEquals(serviceFeePrice, price);
  }

  @Test
  public void add_shouldAddValue() {
    Price addedValue = Price.valueOf(200);

    price = price.add(Price.valueOf(100));

    assertEquals(addedValue, price);
  }

  @Test
  public void multiply_shouldMultiplyValue() {
    Price multipliedValue = Price.valueOf(500);

    price = price.multiply(BigDecimal.valueOf(5));

    assertEquals(multipliedValue, price);
  }

  @Test
  public void divide_shouldDivideValue() {
    Price dividedValue = Price.valueOf(20);

    price = price.divide(BigDecimal.valueOf(5), RoundingMode.DOWN);

    assertEquals(dividedValue, price);
  }

  @ParameterizedTest
  @MethodSource("provideDividedValues")
  public void divide_shouldRoundValue(
      BigDecimal divisor, RoundingMode roundingMode, BigDecimal dividedValue) {
    price = price.divide(divisor, roundingMode);

    assertEquals(dividedValue, price.toBigDecimal());
  }

  private static Stream<Arguments> provideDividedValues() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(3), RoundingMode.DOWN, BigDecimal.valueOf(33.3)),
        Arguments.of(BigDecimal.valueOf(3), RoundingMode.UP, BigDecimal.valueOf(33.4)));
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotPrice() {
    Object object = new Object();

    boolean result = price.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    Price otherPrice = Price.valueOf(50);

    boolean result = price.equals(otherPrice);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    Price otherPrice = Price.valueOf(price.toDouble());

    boolean result = price.equals(otherPrice);

    assertTrue(result);
  }

  @Test
  public void hashCode_shouldValueHashCode() {
    int valueHashCode = price.toBigDecimal().hashCode();

    int hashCode = price.hashCode();

    assertEquals(valueHashCode, hashCode);
  }
}
