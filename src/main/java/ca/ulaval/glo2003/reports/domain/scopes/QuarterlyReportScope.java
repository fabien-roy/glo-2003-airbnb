package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.bookings.domain.BookingPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.ArrayList;
import java.util.List;

public class QuarterlyReportScope extends ReportScope {

  public QuarterlyReportScope(BookingPeriod bookingPeriod) {
    super(bookingPeriod);
  }

  // TODO : QuarterlyReportScope.getReportPeriods()
  @Override
  public List<ReportPeriod> getReportPeriods() {
    return new ArrayList<>();
  }
}
