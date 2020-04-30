package ca.ulaval.glo2003.reports.domain.metrics;

public interface ReportMetricData<T extends Number> {

  ReportMetrics getName();

  T getValue();

  ReportMetricFormats getFormat();
}
