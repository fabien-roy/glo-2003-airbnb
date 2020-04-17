package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.transactions.domain.Price;

public class IncomesReportMetric extends ReportMetric<Price> {

  @Override
  public ReportMetrics getMetricName() {
    return ReportMetrics.CANCELATIONS;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    // TODO
  }
}
