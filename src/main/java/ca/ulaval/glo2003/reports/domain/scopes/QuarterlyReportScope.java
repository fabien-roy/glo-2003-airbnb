package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeQuarter;

import java.util.List;
import java.util.stream.Collectors;

public class QuarterlyReportScope extends ReportScope {

  public QuarterlyReportScope(TimePeriod timePeriod) {
    super(timePeriod);
  }

  @Override
  public List<ReportPeriod> getReportPeriods() {
    return period.getQuarters().stream().map(this::createReportPeriod).collect(Collectors.toList());
  }

  private ReportPeriod createReportPeriod(TimeQuarter quarter) {
    return new ReportPeriod(quarter.toString(), quarter.toPeriod());
  }
}
