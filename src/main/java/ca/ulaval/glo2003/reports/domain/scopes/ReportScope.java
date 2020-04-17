package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.List;

public abstract class ReportScope {

  protected final ReservationPeriod period;

  public ReportScope(ReservationPeriod period) {
    this.period = period;
  }

  public abstract List<ReportPeriod> getReportPeriods();
}
