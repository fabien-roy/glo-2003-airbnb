package ca.ulaval.glo2003.reports.infrastructure.metrics;

import ca.ulaval.glo2003.reports.domain.metrics.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryReportMetricBuilder implements ReportMetricBuilder {

  private List<ReportMetrics> metricTypes = new ArrayList<>();

  public InMemoryReportMetricBuilder someMetrics() {
    return new InMemoryReportMetricBuilder();
  }

  public InMemoryReportMetricBuilder withTypes(List<ReportMetrics> metricTypes) {
    this.metricTypes = metricTypes;
    return this;
  }

  public List<ReportMetric> buildMany() {
    return metricTypes.stream().map(this::buildOne).collect(Collectors.toList());
  }

  private ReportMetric buildOne(ReportMetrics metricType) {
    switch (metricType) {
      case RESERVATIONS:
        return new InMemoryReservationsMetric();
      case CANCELATIONS:
        return new InMemoryCancelationsMetric();
      default:
      case INCOMES:
        return new InMemoryIncomesMetric();
    }
  }
}
