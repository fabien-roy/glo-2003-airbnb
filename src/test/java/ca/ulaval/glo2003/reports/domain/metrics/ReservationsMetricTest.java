package ca.ulaval.glo2003.reports.domain.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReservationsMetricTest extends ReservationFilteringMetricTest {

  @Override
  protected ReportMetrics metricName() {
    return ReportMetrics.RESERVATIONS;
  }

  @BeforeAll
  public static void setUpMetric() {
    metric = new ReservationsMetric();
  }

  @Test
  public void calculate_withoutReservation_shouldCalculateZero() {
    setUpDataWithoutReservation();

    metric.calculate(data);

    assertEquals(0, data.getMetrics().get(0).getValue());
  }

  @Test
  public void calculate_withSingleReservation_shouldCalculateOne() {
    setUpDataWithSingleReservation();

    metric.calculate(data);

    assertEquals(1, data.getMetrics().get(0).getValue());
  }

  @Test
  public void calculate_withMultipleReservations_shouldCalculateNumberOfReservations() {
    setUpDataWithMultipleReservations();

    metric.calculate(data);

    assertEquals(2, data.getMetrics().get(0).getValue());
  }
}
