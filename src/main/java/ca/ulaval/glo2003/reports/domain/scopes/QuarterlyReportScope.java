package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.ArrayList;
import java.util.List;

public class QuarterlyReportScope extends ReportScope {

  public QuarterlyReportScope(TimePeriod timePeriod) {
    super(timePeriod);
  }

  // TODO : QuarterlyReportScope.getReportPeriods()
  @Override
  public List<ReportPeriod> getReportPeriods() {
    return new ArrayList<>();
  }
}
