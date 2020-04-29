package ca.ulaval.glo2003.reports.infrastructure;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.reports.domain.ReportRepository;
import java.util.ArrayList;
import java.util.List;

public class InMemoryReportRepository implements ReportRepository<InMemoryReportQuery> {

  private List<ReportEvent> events = new ArrayList<>();

  @Override
  public void addEvent(ReportEvent event) {
    events.add(event);
  }

  @Override
  public List<ReportPeriod> getPeriods(InMemoryReportQuery query) {
    query.setEvents(events);
    return query.execute();
  }

  @Override
  public void deleteAll() {
    events.clear();
  }
}
