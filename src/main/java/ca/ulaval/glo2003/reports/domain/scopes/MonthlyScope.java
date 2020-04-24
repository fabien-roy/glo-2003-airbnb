package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.time.domain.TimeCalendar;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.List;

public class MonthlyScope extends ReportScope {

  public MonthlyScope(TimePeriod timePeriod) {
    super(timePeriod);
  }

  @Override
  protected List<TimeCalendar> getCalendars() {
    return period.getMonths();
  }
}
