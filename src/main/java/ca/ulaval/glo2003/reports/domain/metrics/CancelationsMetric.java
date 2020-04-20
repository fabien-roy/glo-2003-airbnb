package ca.ulaval.glo2003.reports.domain.metrics;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportEventTypes;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;

import java.util.List;
import java.util.stream.Collectors;

public class CancelationsMetric extends ReportMetric<Integer> {

  @Override
  public ReportMetrics getName() {
    return ReportMetrics.CANCELATIONS;
  }

  @Override
  public void calculate(ReportPeriodData data) {
    List<ReportEvent> events = filterCancelations(data);
    data.addMetric(toMetricData(events.size()));
  }

  private List<ReportEvent> filterCancelations(ReportPeriodData data) {
    return data.getEvents().stream().filter(this::isCancelation).collect(Collectors.toList());
  }

  public boolean isCancelation(ReportEvent event) {
    return event.getType().equals(ReportEventTypes.CANCELATION);
  }
}
