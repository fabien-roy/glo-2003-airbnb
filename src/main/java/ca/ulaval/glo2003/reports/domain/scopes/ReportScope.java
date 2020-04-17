package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.bookings.domain.BookingPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.List;

public abstract class ReportScope {

  private final BookingPeriod bookingPeriod;

  public ReportScope(BookingPeriod bookingPeriod) {
    this.bookingPeriod = bookingPeriod;
  }

  public abstract List<ReportPeriod> getReportPeriods();
}
