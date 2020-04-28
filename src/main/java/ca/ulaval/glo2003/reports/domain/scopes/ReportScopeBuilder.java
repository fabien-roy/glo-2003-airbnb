package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;

public class ReportScopeBuilder {

  private ReportScopes scopeType = ReportScopes.MONTHLY;
  private TimePeriod period = TimeDate.now().toPeriod();

  public ReportScopeBuilder aScope() {
    return new ReportScopeBuilder();
  }

  public ReportScopeBuilder withType(ReportScopes reportScopeType) {
    this.scopeType = reportScopeType;
    return this;
  }

  public ReportScopeBuilder withPeriod(TimePeriod timePeriod) {
    this.period = timePeriod;
    return this;
  }

  public ReportScope build() {
    switch (scopeType) {
      case WEEKLY:
        return new WeeklyScope(period);
      case QUARTERLY:
        return new QuarterlyScope(period);
      case ANNUAL:
        return new AnnualScope(period);
      default:
      case MONTHLY:
        return new MonthlyScope(period);
    }
  }
}
