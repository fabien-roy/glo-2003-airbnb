package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportEventTypes;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportEventBuilder.aReportEvent;
import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodDataBuilder.aReportPeriodData;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ReportMetricTest {

  protected static ReportEvent reservationEvent = aReportEvent().withType(ReportEventTypes.RESERVATION).build();
  protected static ReportEvent otherReservationEvent = aReportEvent().withType(ReportEventTypes.RESERVATION).build();
  protected static ReportEvent cancelationEvent = aReportEvent().withType(ReportEventTypes.CANCELATION).build();
  protected static ReportEvent otherCancelationEvent = aReportEvent().withType(ReportEventTypes.CANCELATION).build();

  protected static ReportMetric metric;
  protected static ReportPeriodData data;

  protected abstract ReportMetrics metricName();

  @BeforeEach
  public void setUpData() {
    data = aReportPeriodData().build();
  }

  @Test
  public void calculate_shouldCalculateWithMetricName() {
    metric.calculate(data);

    assertEquals(1, data.getMetrics().size());
    assertEquals(metricName(), data.getMetrics().get(0).getName());
  }

  @Test
  public void calculate_withoutEvent_shouldCalculateZero() {
    metric.calculate(data);

    assertEquals(0, data.getMetrics().get(0).getValue());
  }
}
