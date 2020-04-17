package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;

// TODO : Test ReportScopeBuilder
public class ReportScopeBuilder {

  private ReportScopes DEFAULT_REPORT_SCOPE_TYPE = ReportScopes.MONTHLY;
  private ReportScopes reportScopeType = DEFAULT_REPORT_SCOPE_TYPE;

  // TODO : This information should not be here
  private ReservationPeriod DEFAULT_BOOKING_PERIOD = ReservationPeriod.ofYear(2020);
  private ReservationPeriod reservationPeriod = DEFAULT_BOOKING_PERIOD;

  public ReportScopeBuilder aReportScope() {
    return new ReportScopeBuilder();
  }

  public ReportScopeBuilder withType(ReportScopes reportScopeType) {
    this.reportScopeType = reportScopeType;
    return this;
  }

  public ReportScopeBuilder withBookingPeriod(ReservationPeriod reservationPeriod) {
    this.reservationPeriod = reservationPeriod;
    return this;
  }

  public ReportScope build() {
    switch (reportScopeType) {
      case WEEKLY:
        return new WeeklyReportScope(reservationPeriod);
      case QUARTERLY:
        return new QuarterlyReportScope(reservationPeriod);
      case ANNUAL:
        return new AnnualReportScope(reservationPeriod);
      default:
      case MONTHLY:
        return new MonthlyReportScope(reservationPeriod);
    }
  }
}
