package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.bookings.domain.BookingPeriod;

// TODO : Test ReportScopeBuilder
public class ReportScopeBuilder {

  private ReportScopes DEFAULT_REPORT_SCOPE_TYPE = ReportScopes.MONTHLY;
  private ReportScopes reportScopeType = DEFAULT_REPORT_SCOPE_TYPE;

  // TODO : This information should not be here
  private BookingPeriod DEFAULT_BOOKING_PERIOD = BookingPeriod.ofYear(2020);
  private BookingPeriod bookingPeriod = DEFAULT_BOOKING_PERIOD;

  public ReportScopeBuilder aReportScope() {
    return new ReportScopeBuilder();
  }

  public ReportScopeBuilder withType(ReportScopes reportScopeType) {
    this.reportScopeType = reportScopeType;
    return this;
  }

  public ReportScopeBuilder withBookingPeriod(BookingPeriod bookingPeriod) {
    this.bookingPeriod = bookingPeriod;
    return this;
  }

  public ReportScope build() {
    switch (reportScopeType) {
      case WEEKLY:
        return new WeeklyReportScope(bookingPeriod);
      case QUARTERLY:
        return new QuarterlyReportScope(bookingPeriod);
      case ANNUAL:
        return new AnnualReportScope(bookingPeriod);
      default:
      case MONTHLY:
        return new MonthlyReportScope(bookingPeriod);
    }
  }
}
