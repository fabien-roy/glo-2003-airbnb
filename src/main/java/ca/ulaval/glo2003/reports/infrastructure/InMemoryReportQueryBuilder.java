package ca.ulaval.glo2003.reports.infrastructure;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class InMemoryReportQueryBuilder implements ReportQueryBuilder<InMemoryReportQuery> {

  private final ReportScopeBuilder scopeBuilder;
  private final ReportMetricBuilder metricBuilder;
  private final ReportDimensionBuilder dimensionBuilder;

  private ReportScopes DEFAULT_SCOPE = ReportScopes.MONTHLY;
  private ReportScopes scope = DEFAULT_SCOPE;

  private TimePeriod DEFAULT_PERIOD = new TimeYear(Year.of(2020)).toPeriod();
  private TimePeriod period = DEFAULT_PERIOD;

  private List<ReportMetrics> DEFAULT_METRICS = Collections.singletonList(ReportMetrics.INCOMES);
  private List<ReportMetrics> metrics = DEFAULT_METRICS;

  private List<ReportDimensions> DEFAULT_DIMENSIONS = Collections.emptyList();
  private List<ReportDimensions> dimensions = DEFAULT_DIMENSIONS;

  @Inject
  public InMemoryReportQueryBuilder(
      ReportScopeBuilder scopeBuilder,
      ReportMetricBuilder metricBuilder,
      ReportDimensionBuilder dimensionBuilder) {
    this.scopeBuilder = scopeBuilder;
    this.metricBuilder = metricBuilder;
    this.dimensionBuilder = dimensionBuilder;
  }

  public InMemoryReportQueryBuilder aReportQuery() {
    return new InMemoryReportQueryBuilder(scopeBuilder, metricBuilder, dimensionBuilder);
  }

  public InMemoryReportQueryBuilder withScope(ReportScopes scope) {
    this.scope = scope;
    return this;
  }

  public InMemoryReportQueryBuilder withMetrics(List<ReportMetrics> metrics) {
    this.metrics = metrics;
    return this;
  }

  public InMemoryReportQueryBuilder withDimensions(List<ReportDimensions> dimensions) {
    this.dimensions = dimensions;
    return this;
  }

  public InMemoryReportQuery build() {
    return new InMemoryReportQuery(
        scopeBuilder.aScope().withType(scope).withPeriod(period).build(),
        metricBuilder.someMetrics().withTypes(metrics).buildMany(),
        dimensionBuilder.someDimensions().withTypes(dimensions).buildMany());
  }
}
