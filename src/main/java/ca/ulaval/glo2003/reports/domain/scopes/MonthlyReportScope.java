package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimeMonth;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.List;
import java.util.stream.Collectors;

public class MonthlyReportScope extends ReportScope {

  public MonthlyReportScope(TimePeriod timePeriod) {
    super(timePeriod);
  }

  @Override
  public List<ReportPeriod> getReportPeriods() {
    return period.getMonths().stream().map(this::createReportPeriod).collect(Collectors.toList());
  }

  private ReportPeriod createReportPeriod(TimeMonth month) {
    return new ReportPeriod(month.toString(), month.toPeriod());
  }
}
