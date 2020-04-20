package ca.ulaval.glo2003.reports.domain.metrics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CancelationsMetricTest extends ReportMetricTest {

  @Override
  protected ReportMetrics metricName() {
    return ReportMetrics.CANCELATIONS;
  }

  @BeforeAll
  public static void setUpMetric() {
    metric = new CancelationsMetric();
  }

  @ParameterizedTest
  @ValueSource(ints = { 0, 3, 5, 10 })
  public void calculate_withCancelations_shouldCalculateNumberOfCancelations(int numberOfCancelations) {
    data = aReportPeriodData().withANumberOfCancelations(numberOfCancelations).build();

    metric.calculate(data);

    assertEquals(numberOfCancelations, data.getMetrics().get(0).getValue());
  }

  @ParameterizedTest
  @ValueSource(ints = { 0, 3, 5, 10 })
  public void calculate_withCancelationsAndReservations_shouldCalculateNumberOfCancelations(int numberOfType) {
    data = aReportPeriodData().withANumberOfCancelations(numberOfType).withANumberOfReservations(numberOfType).build();

    metric.calculate(data);

    assertEquals(numberOfType, data.getMetrics().get(0).getValue());
  }
}
