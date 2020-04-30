package ca.ulaval.glo2003.reports.infrastructure.metrics;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricFormats;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import ca.ulaval.glo2003.transactions.domain.Price;
import java.util.List;

public class InMemoryIncomesMetric extends InMemoryReservationFilteringMetric<Double> {

  @Override
  public ReportMetrics getName() {
    return ReportMetrics.INCOMES;
  }

  @Override
  public ReportMetricFormats getFormat() {
    return ReportMetricFormats.DOUBLE;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    List<ReportEvent> reservations = filterReservations(data);
    data.addMetric(toMetricData(sumIncomes(reservations).toDouble()));
  }

  private Price sumIncomes(List<ReportEvent> reservations) {
    Price incomes = Price.zero();
    for (ReportEvent reservation : reservations) incomes = incomes.add(reservation.getIncomes());
    return incomes;
  }
}
