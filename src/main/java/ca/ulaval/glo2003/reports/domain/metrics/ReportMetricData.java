package ca.ulaval.glo2003.reports.domain.metrics;

public interface ReportMetricData<T> {

  ReportMetrics getName();

  T getValue();
}
