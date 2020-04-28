package ca.ulaval.glo2003.reports.domain.metrics;

import java.util.List;

public interface ReportMetricBuilder {

  ReportMetricBuilder someMetrics();

  ReportMetricBuilder withTypes(List<ReportMetrics> metricTypes);

  List<ReportMetric> buildMany();
}
