package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.List;

public abstract class ReportScope {

  protected final TimePeriod period;

  public ReportScope(TimePeriod period) {
    this.period = period;
  }

  public abstract List<ReportPeriod> getPeriods();
}
