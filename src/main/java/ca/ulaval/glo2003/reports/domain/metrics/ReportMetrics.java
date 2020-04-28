package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.exceptions.InvalidReportMetricsException;
import java.util.HashMap;
import java.util.Map;

public enum ReportMetrics {
  INCOMES("incomes"),
  RESERVATIONS("reservations"),
  CANCELATIONS("cancelations");

  private String metric;
  private static final Map<String, ReportMetrics> lookup = new HashMap<>();

  static {
    for (ReportMetrics metric : ReportMetrics.values()) {
      lookup.put(metric.toString(), metric);
    }
  }

  ReportMetrics(String metric) {
    this.metric = metric;
  }

  @Override
  public String toString() {
    return metric;
  }

  public static ReportMetrics get(String metric) {
    ReportMetrics foundMetric = lookup.get(metric);

    if (foundMetric == null) throw new InvalidReportMetricsException();

    return foundMetric;
  }
}
