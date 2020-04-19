package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

// TODO : Test ReportQueryBuilder
public class ReportQueryBuilder {

  private final ReportScopeBuilder scopeBuilder;
  private final ReportDimensionBuilder dimensionBuilder;
  private final ReportMetricBuilder metricBuilder;

  private ReportScopes DEFAULT_SCOPE = ReportScopes.MONTHLY;
  private ReportScopes scope = DEFAULT_SCOPE;

  private TimePeriod DEFAULT_PERIOD = new TimeYear(Year.of(2020)).toPeriod();
  private TimePeriod period = DEFAULT_PERIOD;

  private List<ReportMetrics> DEFAULT_METRICS = Collections.singletonList(ReportMetrics.INCOMES);
  private List<ReportMetrics> metrics = DEFAULT_METRICS;

  private List<ReportDimensions> DEFAULT_DIMENSIONS = new ArrayList<>();
  private List<ReportDimensions> dimensions = DEFAULT_DIMENSIONS;

  @Inject
  public ReportQueryBuilder(
      ReportScopeBuilder scopeBuilder,
      ReportMetricBuilder metricBuilder,
      ReportDimensionBuilder dimensionBuilder) {
    this.scopeBuilder = scopeBuilder;
    this.metricBuilder = metricBuilder;
    this.dimensionBuilder = dimensionBuilder;
  }

  public ReportQueryBuilder aReportQuery() {
    return new ReportQueryBuilder(scopeBuilder, metricBuilder, dimensionBuilder);
  }

  public ReportQueryBuilder withScope(ReportScopes scope) {
    this.scope = scope;
    return this;
  }

  public ReportQueryBuilder withMetrics(List<ReportMetrics> metrics) {
    this.metrics = metrics;
    return this;
  }

  public ReportQueryBuilder withDimensions(List<ReportDimensions> dimensions) {
    this.dimensions = dimensions;
    return this;
  }

  public ReportQuery build() {
    return new ReportQuery(
        scopeBuilder.aScope().withType(scope).withPeriod(period).build(),
        metricBuilder.someMetrics().withTypes(metrics).buildMany(),
        dimensionBuilder.someDimensions().withTypes(dimensions).buildMany());
  }
}
