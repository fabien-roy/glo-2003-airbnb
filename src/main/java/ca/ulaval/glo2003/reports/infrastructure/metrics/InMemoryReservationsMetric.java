package ca.ulaval.glo2003.reports.infrastructure.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;

public class InMemoryReservationsMetric extends InMemoryReservationFilteringMetric<Integer> {

  @Override
  public ReportMetrics getName() {
    return ReportMetrics.RESERVATIONS;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    Integer reservations = filterReservations(data).size();
    data.addMetric(toMetricData(reservations));
  }
}
