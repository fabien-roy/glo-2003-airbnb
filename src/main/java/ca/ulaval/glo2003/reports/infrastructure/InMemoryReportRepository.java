package ca.ulaval.glo2003.reports.infrastructure;

import ca.ulaval.glo2003.reports.domain.ReportEvent;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.reports.domain.ReportQuery;
import ca.ulaval.glo2003.reports.domain.ReportRepository;
import java.util.ArrayList;
import java.util.List;

// TODO : Test InMemoryReportRepository
public class InMemoryReportRepository implements ReportRepository<ReportQuery> {

  private List<ReportEvent> events = new ArrayList<>();

  @Override
  public void addEvent(ReportEvent event) {
    events.add(event);
  }

  @Override
  public List<ReportPeriod> getPeriods(ReportQuery query) {
    query.setEvents(events);
    return query.execute();
  }
}
