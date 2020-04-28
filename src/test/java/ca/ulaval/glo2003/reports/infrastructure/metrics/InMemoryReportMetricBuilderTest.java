package ca.ulaval.glo2003.reports.infrastructure.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.ulaval.glo2003.reports.domain.metrics.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InMemoryReportMetricBuilderTest {

  private static ReportMetricBuilder metricBuilder;

  @BeforeAll
  public static void setUpBuilder() {
    metricBuilder = new InMemoryReportMetricBuilder();
  }

  @Test
  public void buildMany_withoutMetrics_shouldBuildNoMetric() {
    List<ReportMetric> metrics = metricBuilder.someMetrics().buildMany();

    assertEquals(0, metrics.size());
  }

  @ParameterizedTest
  @MethodSource("provideMetrics")
  public void buildMany_withMetric_shouldBuildMetric(
      ReportMetrics metricType, Class<? extends ReportMetric> metricClass) {
    List<ReportMetrics> metricTypes = Collections.singletonList(metricType);

    List<ReportMetric> metrics = metricBuilder.someMetrics().withTypes(metricTypes).buildMany();

    assertEquals(1, metrics.size());
    assertTrue(metricClass.isInstance(metrics.get(0)));
  }

  private static Stream<Arguments> provideMetrics() {
    return Stream.of(
        Arguments.of(ReportMetrics.INCOMES, InMemoryIncomesMetric.class),
        Arguments.of(ReportMetrics.RESERVATIONS, InMemoryReservationsMetric.class),
        Arguments.of(ReportMetrics.CANCELATIONS, InMemoryCancelationsMetric.class));
  }

  @Test
  public void buildMany_withMultipleMetrics_shouldBuildMetrics() {
    List<ReportMetrics> metricTypes =
        Arrays.asList(
            ReportMetrics.INCOMES, ReportMetrics.RESERVATIONS, ReportMetrics.CANCELATIONS);

    List<ReportMetric> metrics = metricBuilder.someMetrics().withTypes(metricTypes).buildMany();

    assertEquals(3, metrics.size());
    assertTrue(metrics.stream().anyMatch(metric -> metric instanceof InMemoryIncomesMetric));
    assertTrue(metrics.stream().anyMatch(metric -> metric instanceof InMemoryReservationsMetric));
    assertTrue(metrics.stream().anyMatch(metric -> metric instanceof InMemoryCancelationsMetric));
  }
}
