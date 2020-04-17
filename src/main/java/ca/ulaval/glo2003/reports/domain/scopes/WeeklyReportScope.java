package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.ArrayList;
import java.util.List;

public class WeeklyReportScope extends ReportScope {

  public WeeklyReportScope(ReservationPeriod reservationPeriod) {
    super(reservationPeriod);
  }

  // TODO : WeeklyReportScope.getReportPeriods()
  @Override
  public List<ReportPeriod> getReportPeriods() {
    return new ArrayList<>();
  }
}
