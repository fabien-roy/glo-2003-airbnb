package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.transactions.domain.Price;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncomesMetricTest extends ReportMetricTest {

  @Override
  protected ReportMetrics metricName() {
    return ReportMetrics.INCOMES;
  }

  @BeforeAll
  public static void setUpMetric() {
    metric = new IncomesMetric();
  }

  @ParameterizedTest
  @MethodSource("provideIncomes")
  public void calculate_withIncomes_shouldCalculateIncomes(int numberOfReservations, Price incomes, Price sumIncomes) {
    data = aReportPeriodData().withANumberOfReservations(numberOfReservations).withIncomes(incomes).build();

    metric.calculate(data);

    assertEquals(sumIncomes, data.getMetrics().get(0).getValue());
  }

  @ParameterizedTest
  @MethodSource("provideIncomes")
  public void calculate_withIncomesAndCancelations_shouldCalculateIncomes(int numberOfType, Price incomes, Price sumIncomes) {
    data = aReportPeriodData().withANumberOfReservations(numberOfType).withANumberOfCancelations(numberOfType).withIncomes(incomes).build();

    metric.calculate(data);

    assertEquals(sumIncomes, data.getMetrics().get(0).getValue());
  }

  private static Stream<Arguments> provideIncomes() {
    return Stream.of(
        Arguments.of(0, Price.zero(), Price.zero()),
        Arguments.of(3, new Price(123.123), new Price(369.369)),
        Arguments.of(5, new Price(49.99), new Price(249.95)));
  }
}
