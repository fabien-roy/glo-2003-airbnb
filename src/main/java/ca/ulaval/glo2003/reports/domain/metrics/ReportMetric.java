package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;

// TODO : Test ReportMetric
public abstract class ReportMetric<T> {

  public abstract ReportMetrics getMetricName();

  public abstract void calculate(ReportPeriodData data);

  protected ReportMetricData<T> toMetricData(T value) {
    return new ReportMetricData<T>() {
      @Override
      public ReportMetrics getName() {
        return getMetricName();
      }

      @Override
      public T getValue() {
        return value;
      }
    };
  }
}
