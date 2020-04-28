package ca.ulaval.glo2003.reports.domain.assemblers;

import static ca.ulaval.glo2003.reports.domain.assemblers.MetricsQueryParamAssembler.METRICS_PARAM;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo2003.reports.domain.ReportQueryBuilder;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetrics;
import ca.ulaval.glo2003.reports.exceptions.InvalidReportMetricsException;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MetricsQueryParamAssemblerTest {

  private static ReportQueryParamAssembler queryAssembler;
  private static ReportQueryBuilder queryBuilder = mock(ReportQueryBuilder.class);
  private static ReportQueryBuilder assembledQueryBuilder = mock(ReportQueryBuilder.class);

  private ReportMetrics metric = ReportMetrics.INCOMES;
  private ReportMetrics otherMetric = ReportMetrics.CANCELATIONS;
  private List<ReportMetrics> singleMetric = Collections.singletonList(metric);
  private List<ReportMetrics> multipleMetrics = Arrays.asList(metric, otherMetric);
  private List<ReportMetrics> duplicatedMetrics = Arrays.asList(metric, metric);
  private Map<String, List<String>> params = new HashMap<>();

  @BeforeAll
  public static void setUpAssembler() {
    queryAssembler = new MetricsQueryParamAssembler();
  }

  @Test
  public void assemble_withoutMetrics_shouldReturnSameBuilder() {
    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(queryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withSingleMetric_shouldAssembleBuilder() {
    params.put(METRICS_PARAM, toParam(singleMetric));
    when(queryBuilder.withMetrics(singleMetric)).thenReturn(assembledQueryBuilder);

    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withMultipleMetrics_shouldAssembleBuilder() {
    params.put(METRICS_PARAM, toParam(multipleMetrics));
    when(queryBuilder.withMetrics(multipleMetrics)).thenReturn(assembledQueryBuilder);

    ReportQueryBuilder actualQueryBuilder = queryAssembler.assemble(queryBuilder, params);

    assertSame(assembledQueryBuilder, actualQueryBuilder);
  }

  @Test
  public void assemble_withInvalidMetric_shouldThrowInvalidReportMetricException() {
    params.put(METRICS_PARAM, Collections.singletonList("invalidMetrics"));

    assertThrows(
        InvalidReportMetricsException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }

  @Test
  public void assemble_withDuplicatedMetrics_shouldThrowInvalidReportMetricException() {
    params.put(METRICS_PARAM, toParam(duplicatedMetrics));

    assertThrows(
        InvalidReportMetricsException.class, () -> queryAssembler.assemble(queryBuilder, params));
  }

  private List<String> toParam(List<ReportMetrics> metrics) {
    return metrics.stream().map(ReportMetrics::toString).collect(Collectors.toList());
  }
}
