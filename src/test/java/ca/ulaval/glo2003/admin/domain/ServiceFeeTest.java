package ca.ulaval.glo2003.admin.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ServiceFeeTest {

  @ParameterizedTest
  @MethodSource("provideNullFees")
  public void getFor_withNoFee_shouldReturnZero(Price price) {
    ServiceFee serviceFee = new ServiceFee(null);

    Price actualTotal = serviceFee.getFor(price);

    assertEquals(Price.zero(), actualTotal);
  }

  private static Stream<Arguments> provideNullFees() {
    return Stream.of(
        Arguments.of(new Price(123.123)),
        Arguments.of(new Price(100.0)),
        Arguments.of(new Price(100.0)));
  }

  @ParameterizedTest
  @MethodSource("provideFees")
  public void getFor_withFees_shouldReturnFees(BigDecimal feeFactor, Price price, Price fees) {
    ServiceFee serviceFee = new ServiceFee(feeFactor);

    Price actualFees = serviceFee.getFor(price);

    assertEquals(fees, actualFees);
  }

  private static Stream<Arguments> provideFees() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(0), new Price(123.123), Price.zero()),
        Arguments.of(BigDecimal.valueOf(15), new Price(100.0), new Price(15.00)),
        Arguments.of(BigDecimal.valueOf(15.15), new Price(100.0), new Price(15.15)));
  }
}
