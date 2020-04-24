package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.time.domain.TimeCalendar;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.List;

public class AnnualScope extends ReportScope {

  public AnnualScope(TimePeriod period) {
    super(period);
  }

  @Override
  protected List<TimeCalendar> getCalendars() {
    return period.getYears();
  }
}
