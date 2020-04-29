package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportPeriodData;

public abstract class ReportMetric<T extends Number> {

  public abstract ReportMetrics getName();

  public abstract ReportMetricFormats getFormat();

  public abstract void calculate(ReportPeriodData data);

  protected ReportMetricData<T> toMetricData(T value) {
    return new ReportMetricData<T>() {
      @Override
      public ReportMetrics getName() {
        return ReportMetric.this.getName();
      }

      @Override
      public T getValue() {
        return value;
      }

      @Override
      public ReportMetricFormats getFormat() {
        return ReportMetric.this.getFormat();
      }
    };
  }
}
