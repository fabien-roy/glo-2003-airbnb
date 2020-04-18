package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.ArrayList;
import java.util.List;

public class WeeklyReportScope extends ReportScope {

  public WeeklyReportScope(TimePeriod timePeriod) {
    super(timePeriod);
  }

  // TODO : WeeklyReportScope.getReportPeriods()
  @Override
  public List<ReportPeriod> getReportPeriods() {
    return new ArrayList<>();
  }
}
