package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.admin.domain.Configuration;
import ca.ulaval.glo2003.admin.domain.ServiceFee;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Price;

public class IncomesMetric extends ReservationFilteringMetric<Price> {

  @Override
  public ReportMetrics getName() {
    return ReportMetrics.INCOMES;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    ServiceFee serviceFee = Configuration.instance().getServiceFee();

    if (serviceFee.isSet()) {
      int reservations = filterReservations(data).size();
      Price incomes = new Price(reservations).multiply(serviceFee.toBigDecimal());
      data.addMetrics(toMetricData(incomes));
    } else {
      data.addMetrics(toMetricData(Price.zero()));
    }
  }
}
