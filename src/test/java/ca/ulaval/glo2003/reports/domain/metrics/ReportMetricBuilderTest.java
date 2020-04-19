package ca.ulaval.glo2003.reports.domain.metrics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReportMetricBuilderTest {

  private static ReportMetricBuilder metricBuilder;

  @BeforeAll
  public static void setUpBuilder() {
    metricBuilder = new ReportMetricBuilder();
  }

  @Test
  public void buildMany_withoutMetrics_shouldBuildNoMetric() {
    List<ReportMetric> metrics = metricBuilder.someMetrics().buildMany();

    assertEquals(0, metrics.size());
  }

  @Test
  public void buildMany_withIncomesMetric_shouldBuildIncomesMetric() {
    List<ReportMetrics> metricTypes = Collections.singletonList(ReportMetrics.INCOMES);

    List<ReportMetric> metrics = metricBuilder.someMetrics().withTypes(metricTypes).buildMany();

    assertEquals(1, metrics.size());
    assertTrue(metrics.get(0) instanceof IncomesMetric);
  }

  @Test
  public void buildMany_withReservationsMetric_shouldBuildReservationsMetric() {
    List<ReportMetrics> metricTypes = Collections.singletonList(ReportMetrics.RESERVATIONS);

    List<ReportMetric> metrics = metricBuilder.someMetrics().withTypes(metricTypes).buildMany();

    assertEquals(1, metrics.size());
    assertTrue(metrics.get(0) instanceof ReservationsMetric);
  }

  @Test
  public void buildMany_withCancelationsMetric_shouldBuildCancelationsMetric() {
    List<ReportMetrics> metricTypes = Collections.singletonList(ReportMetrics.CANCELATIONS);

    List<ReportMetric> metrics = metricBuilder.someMetrics().withTypes(metricTypes).buildMany();

    assertEquals(1, metrics.size());
    assertTrue(metrics.get(0) instanceof CancelationsMetric);
  }

  @Test
  public void buildMany_withMultipleMetrics_shouldBuildMetrics() {
    List<ReportMetrics> metricTypes =
        Arrays.asList(
            ReportMetrics.INCOMES, ReportMetrics.RESERVATIONS, ReportMetrics.CANCELATIONS);

    List<ReportMetric> metrics = metricBuilder.someMetrics().withTypes(metricTypes).buildMany();

    assertEquals(3, metrics.size());
    assertTrue(metrics.stream().anyMatch(metric -> metric instanceof IncomesMetric));
    assertTrue(metrics.stream().anyMatch(metric -> metric instanceof ReservationsMetric));
    assertTrue(metrics.stream().anyMatch(metric -> metric instanceof CancelationsMetric));
  }
}
