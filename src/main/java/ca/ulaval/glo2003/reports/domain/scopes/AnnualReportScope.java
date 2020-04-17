package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.bookings.domain.BookingPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.ArrayList;
import java.util.List;

public class AnnualReportScope extends ReportScope {

  public AnnualReportScope(BookingPeriod bookingPeriod) {
    super(bookingPeriod);
  }

  // TODO : AnnualReportScope.getReportPeriods()
  @Override
  public List<ReportPeriod> getReportPeriods() {
    return new ArrayList<>();
  }
}
