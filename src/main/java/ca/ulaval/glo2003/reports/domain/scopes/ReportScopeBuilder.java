package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.TimeYear;
import java.time.Year;

// TODO : Test ReportScopeBuilder
public class ReportScopeBuilder {

  private ReportScopes DEFAULT_REPORT_SCOPE_TYPE = ReportScopes.MONTHLY;
  private ReportScopes reportScopeType = DEFAULT_REPORT_SCOPE_TYPE;

  // TODO : This information should not be here
  private TimePeriod DEFAULT_BOOKING_PERIOD = new TimeYear(Year.of(2020)).toPeriod();
  private TimePeriod timePeriod = DEFAULT_BOOKING_PERIOD;

  public ReportScopeBuilder aReportScope() {
    return new ReportScopeBuilder();
  }

  public ReportScopeBuilder withType(ReportScopes reportScopeType) {
    this.reportScopeType = reportScopeType;
    return this;
  }

  public ReportScopeBuilder withBookingPeriod(TimePeriod timePeriod) {
    this.timePeriod = timePeriod;
    return this;
  }

  public ReportScope build() {
    switch (reportScopeType) {
      case WEEKLY:
        return new WeeklyReportScope(timePeriod);
      case QUARTERLY:
        return new QuarterlyReportScope(timePeriod);
      case ANNUAL:
        return new AnnualReportScope(timePeriod);
      default:
      case MONTHLY:
        return new MonthlyReportScope(timePeriod);
    }
  }
}
