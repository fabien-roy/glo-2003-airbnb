package ca.ulaval.glo2003.reports.domain.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.admin.domain.Configuration;
import ca.ulaval.glo2003.admin.domain.ServiceFee;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class IncomesMetricTest extends ReservationFilteringMetricTest {

  private static ServiceFee serviceFee = mock(ServiceFee.class);

  @Override
  protected ReportMetrics metricName() {
    return ReportMetrics.INCOMES;
  }

  @BeforeAll
  public static void setUpMetric() {
    reset(serviceFee);
    when(serviceFee.toBigDecimal()).thenReturn(BigDecimal.ZERO);
    when(serviceFee.isSet()).thenReturn(true);
    Configuration.instance().setServiceFee(serviceFee);
    metric = new IncomesMetric();
  }

  @Test
  public void calculate_withoutReservation_shouldCalculateZero() {
    setUpDataWithoutReservation();

    metric.calculate(data);

    assertEquals(Price.zero(), data.getMetrics().get(0).getValue());
  }

  @Test
  public void calculate_withoutServiceFee_shouldCalculateZero() {
    when(serviceFee.isSet()).thenReturn(false);
    setUpDataWithMultipleReservations();

    metric.calculate(data);

    assertEquals(Price.zero(), data.getMetrics().get(0).getValue());
  }

  @ParameterizedTest
  @MethodSource("provideSingleReservationIncomes")
  public void calculate_withSingleReservation_shouldCalculateOne(
      BigDecimal serviceFeeValue, Price incomes) {
    when(serviceFee.toBigDecimal()).thenReturn(serviceFeeValue);
    setUpDataWithSingleReservation();

    metric.calculate(data);

    assertEquals(incomes, data.getMetrics().get(0).getValue());
  }

  private static Stream<Arguments> provideSingleReservationIncomes() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(123.123), new Price(123.123)),
        Arguments.of(BigDecimal.valueOf(0.0), new Price(0.0)),
        Arguments.of(BigDecimal.valueOf(49.99), new Price(49.99)));
  }

  @ParameterizedTest
  @MethodSource("provideMultipleReservationsIncomes")
  public void calculate_withMultipleReservations_shouldCalculateNumberOfReservations(
      BigDecimal serviceFeeValue, Price incomes) {
    when(serviceFee.toBigDecimal()).thenReturn(serviceFeeValue);
    setUpDataWithMultipleReservations();

    metric.calculate(data);

    assertEquals(incomes, data.getMetrics().get(0).getValue());
  }

  private static Stream<Arguments> provideMultipleReservationsIncomes() {
    return Stream.of(
        Arguments.of(BigDecimal.valueOf(123.123), new Price(246.246)),
        Arguments.of(BigDecimal.valueOf(0.0), new Price(0.0)),
        Arguments.of(BigDecimal.valueOf(49.99), new Price(99.98)));
  }
}
