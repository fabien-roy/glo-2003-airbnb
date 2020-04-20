package ca.ulaval.glo2003.reports.infrastructure.metrics;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InMemoryReservationsMetricTest extends InMemoryReportMetricTest {

  @Override
  protected ReportMetrics metricName() {
    return ReportMetrics.RESERVATIONS;
  }

  @BeforeAll
  public static void setUpMetric() {
    metric = new InMemoryReservationsMetric();
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 3, 5, 10})
  public void calculate_withReservations_shouldCalculateNumberOfReservations(
      int numberOfReservations) {
    data = aReportPeriodData().withANumberOfReservations(numberOfReservations).build();

    metric.calculate(data);

    assertEquals(numberOfReservations, data.getMetrics().get(0).getValue());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 3, 5, 10})
  public void calculate_withReservationsAndCancelations_shouldCalculateNumberOfReservations(
      int numberOfType) {
    data =
        aReportPeriodData()
            .withANumberOfReservations(numberOfType)
            .withANumberOfCancelations(numberOfType)
            .build();

    metric.calculate(data);

    assertEquals(numberOfType, data.getMetrics().get(0).getValue());
  }

  @Test
  public void calculate_withoutEvent_shouldCalculateZero() {
    metric.calculate(data);

    assertEquals(0, data.getMetrics().get(0).getValue());
  }
}
