package ca.ulaval.glo2003.reports.infrastructure.metrics;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportEventTypes;
import ca.ulaval.glo2003.reports.domain.ReportPeriodData;
import ca.ulaval.glo2003.reports.domain.metrics.ReportMetric;
import java.util.List;
import java.util.stream.Collectors;

public abstract class InMemoryReservationFilteringMetric<T> extends ReportMetric<T> {

  protected List<ReportEvent> filterReservations(ReportPeriodData data) {
    return data.getEvents().stream().filter(this::isReservation).collect(Collectors.toList());
  }

  protected boolean isReservation(ReportEvent event) {
    return event.getType().equals(ReportEventTypes.RESERVATION);
  }
}
