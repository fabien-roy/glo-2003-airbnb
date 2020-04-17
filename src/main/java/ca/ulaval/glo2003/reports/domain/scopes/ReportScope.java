package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.List;

public abstract class ReportScope {

  private final ReservationPeriod reservationPeriod;

  public ReportScope(ReservationPeriod reservationPeriod) {
    this.reservationPeriod = reservationPeriod;
  }

  public abstract List<ReportPeriod> getReportPeriods();
}
