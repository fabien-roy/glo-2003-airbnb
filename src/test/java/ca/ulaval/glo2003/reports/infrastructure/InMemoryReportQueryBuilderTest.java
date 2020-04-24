package ca.ulaval.glo2003.reports.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ca.ulaval.glo2003.admin.domain.Configuration;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimension;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensionBuilder;
import ca.ulaval.glo2003.reports.domain.dimensions.ReportDimensions;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetricBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScope;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopeBuilder;
import ca.ulaval.glo2003.reports.domain.scopes.ReportScopes;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class InMemoryReportQueryBuilderTest {

  private static InMemoryReportQueryBuilder queryBuilder;

  private static ReportScopeBuilder scopeBuilder = mock(ReportScopeBuilder.class);
  private static ReportMetricBuilder metricBuilder = mock(ReportMetricBuilder.class);
  private static ReportDimensionBuilder dimensionBuilder = mock(ReportDimensionBuilder.class);

  private static TimePeriod yearPeriod = new TimeYear(ZonedDateTime.now()).toPeriod();
  private static ReportScope monthlyScopeOfYear = mock(ReportScope.class);
  private static ReportScope scopeOfYear = mock(ReportScope.class);
  private static ReportMetric incomesMetric = mock(ReportMetric.class);
  private static List<ReportMetric> metrics = Collections.singletonList(mock(ReportMetric.class));
  private static List<ReportDimension> dimensions =
      Collections.singletonList(mock(ReportDimension.class));

  @BeforeAll
  public static void setUpBuilder() {
    Configuration.instance().setDefaultReportPeriod(yearPeriod);
    queryBuilder = new InMemoryReportQueryBuilder(scopeBuilder, metricBuilder, dimensionBuilder);
  }

  @BeforeEach
  public void setUpMocks() {
    reset(scopeBuilder, metricBuilder, dimensionBuilder);
    setUpScopeBuilder(ReportScopes.MONTHLY, monthlyScopeOfYear);
    setUpMetricBuilder(
        Collections.singletonList(ReportMetrics.INCOMES), Collections.singletonList(incomesMetric));
    setUpDimensionBuilder(Collections.emptyList(), Collections.emptyList());
  }

  private void setUpScopeBuilder(ReportScopes scopeType, ReportScope returnedScope) {
    when(scopeBuilder.aScope()).thenReturn(scopeBuilder);
    when(scopeBuilder.withType(eq(scopeType))).thenReturn(scopeBuilder);
    when(scopeBuilder.withPeriod(eq(yearPeriod))).thenReturn(scopeBuilder);
    when(scopeBuilder.build()).thenReturn(returnedScope);
  }

  private void setUpMetricBuilder(List<ReportMetrics> metrics, List<ReportMetric> returnedMetrics) {
    when(metricBuilder.someMetrics()).thenReturn(metricBuilder);
    when(metricBuilder.withTypes(eq(metrics))).thenReturn(metricBuilder);
    when(metricBuilder.buildMany()).thenReturn(returnedMetrics);
  }

  private void setUpDimensionBuilder(
      List<ReportDimensions> dimensions, List<ReportDimension> returnedDimensions) {
    when(dimensionBuilder.someDimensions()).thenReturn(dimensionBuilder);
    when(dimensionBuilder.withTypes(eq(dimensions))).thenReturn(dimensionBuilder);
    when(dimensionBuilder.buildMany()).thenReturn(returnedDimensions);
  }

  @Test
  public void build_withoutScope_shouldSetMonthlyScopeOfYear() {
    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(monthlyScopeOfYear, query.getScope());
  }

  @ParameterizedTest
  @EnumSource(ReportScopes.class)
  public void build_withScope_shouldSetScopeOfYear(ReportScopes scopeType) {
    setUpScopeBuilder(scopeType, scopeOfYear);

    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(scopeOfYear, query.getScope());
  }

  @Test
  public void build_withoutMetric_shouldSetMetricToIncomes() {
    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(1, query.getMetrics().size());
    assertEquals(incomesMetric, query.getMetrics().get(0));
  }

  @Test
  public void build_withSingleMetric_shouldSetMetric() {
    List<ReportMetrics> metricTypes = Collections.singletonList(ReportMetrics.RESERVATIONS);
    setUpMetricBuilder(metricTypes, metrics);

    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(metrics, query.getMetrics());
  }

  @Test
  public void build_withMultipleMetrics_shouldSetMetrics() {
    List<ReportMetrics> metricTypes =
        Arrays.asList(ReportMetrics.RESERVATIONS, ReportMetrics.CANCELATIONS);
    setUpMetricBuilder(metricTypes, metrics);

    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(metrics, query.getMetrics());
  }

  @Test
  public void build_withoutDimension_shouldSetNoDimension() {
    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(0, query.getDimensions().size());
  }

  @Test
  public void build_withSingleDimension_shouldSetDimension() {
    List<ReportDimensions> dimensionTypes = Collections.singletonList(ReportDimensions.PACKAGE);
    setUpDimensionBuilder(dimensionTypes, dimensions);

    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(dimensions, query.getDimensions());
  }

  @Test
  public void build_withMultipleDimensions_shouldSetDimensions() {
    List<ReportDimensions> dimensionTypes =
        Arrays.asList(ReportDimensions.PACKAGE, ReportDimensions.LODGING_MODE);
    setUpDimensionBuilder(dimensionTypes, dimensions);

    InMemoryReportQuery query = queryBuilder.build();

    assertEquals(dimensions, query.getDimensions());
  }
}
