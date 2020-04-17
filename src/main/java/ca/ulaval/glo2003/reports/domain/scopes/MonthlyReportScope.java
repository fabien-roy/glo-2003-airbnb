package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.ArrayList;
import java.util.List;

public class MonthlyReportScope extends ReportScope {

  public MonthlyReportScope(ReservationPeriod reservationPeriod) {
    super(reservationPeriod);
  }

  // TODO : MonthlyReportScope.getReportPeriods()
  @Override
  public List<ReportPeriod> getReportPeriods() {
    return new ArrayList<>();
  }
}
