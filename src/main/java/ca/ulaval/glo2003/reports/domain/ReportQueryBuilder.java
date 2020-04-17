package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import java.util.ArrayList;
import java.util.List;

// TODO : Test ReportQueryBuilder
public class ReportQueryBuilder {

  private List<ReportPeriod> DEFAULT_PERIODS = new ArrayList<>();
  private List<ReportPeriod> periods = DEFAULT_PERIODS;

  private List<ReportMetric> DEFAULT_METRICS = new ArrayList<>();
  private List<ReportMetric> metrics = DEFAULT_METRICS;

  private List<ReportDimension> DEFAULT_DIMENSIONS = new ArrayList<>();
  private List<ReportDimension> dimensions = DEFAULT_DIMENSIONS;

  public ReportQueryBuilder aReportQuery() {
    return new ReportQueryBuilder();
  }

  public ReportQueryBuilder withPeriods(List<ReportPeriod> periods) {
    this.periods = periods;
    return this;
  }

  public ReportQueryBuilder withMetrics(List<ReportMetric> metrics) {
    this.metrics = metrics;
    return this;
  }

  public ReportQueryBuilder withDimensions(List<ReportDimension> dimensions) {
    this.dimensions = dimensions;
    return this;
  }

  public ReportQuery build() {
    return new ReportQuery(periods, metrics, dimensions);
  }
}
