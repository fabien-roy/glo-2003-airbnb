package ca.ulaval.glo2003.reports.domain.metrics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO : Test ReportMetricBuilder
public class ReportMetricBuilder {

  private List<ReportMetrics> metricTypes = new ArrayList<>();

  public ReportMetricBuilder someMetrics() {
    return new ReportMetricBuilder();
  }

  public ReportMetricBuilder withTypes(List<ReportMetrics> metricTypes) {
    this.metricTypes = metricTypes;
    return this;
  }

  public List<ReportMetric> buildMany() {
    return metricTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportMetric buildOne(ReportMetrics metricType) {
    switch (metricType) {
      case RESERVATIONS:
        return new ReservationsReportMetric();
      case CANCELATIONS:
        return new CancelationsReportMetric();
      default:
      case INCOMES:
        return new IncomesReportMetric();
    }
  }
}
