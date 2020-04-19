package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeWeek;
import java.util.List;
import java.util.stream.Collectors;

public class WeeklyReportScope extends ReportScope {

  public WeeklyReportScope(TimePeriod timePeriod) {
    super(timePeriod);
  }

  @Override
  public List<ReportPeriod> getPeriods() {
    return period.getWeeks().stream().map(this::createReportPeriod).collect(Collectors.toList());
  }

  private ReportPeriod createReportPeriod(TimeWeek week) {
    return new ReportPeriod(week.toString(), week.toPeriod());
  }
}
