package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;

public class ReservationsMetric extends ReservationFilteringMetric<Integer> {

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
