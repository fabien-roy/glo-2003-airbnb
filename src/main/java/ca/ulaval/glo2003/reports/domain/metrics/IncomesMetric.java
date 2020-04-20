package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Price;

import java.util.List;

public class IncomesMetric extends ReservationFilteringMetric<Price> {

  @Override
  public ReportMetrics getName() {
    return ReportMetrics.INCOMES;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    List<ReportEvent> reservations = filterReservations(data);
    data.addMetric(toMetricData(sumIncomes(reservations)));
  }

  private Price sumIncomes(List<ReportEvent> reservations) {
    Price incomes = Price.zero();
    reservations.forEach(reservation -> incomes.add(reservation.getIncomes()));
    return incomes;
  }
}
