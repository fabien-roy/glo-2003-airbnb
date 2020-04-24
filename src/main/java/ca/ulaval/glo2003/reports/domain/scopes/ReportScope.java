package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimeCalendar;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ReportScope {

  protected final TimePeriod period;

  public ReportScope(TimePeriod period) {
    this.period = period;
  }

  public TimePeriod getPeriod() {
    return period;
  }

  protected abstract List<TimeCalendar> getCalendars();

  public List<ReportPeriod> getReportPeriods() {
    return getCalendars().stream().map(this::createReportPeriod).collect(Collectors.toList());
  }

  private ReportPeriod createReportPeriod(TimeCalendar calendar) {
    return new ReportPeriod(calendar.toString(), calendar.toPeriod());
  }
}
