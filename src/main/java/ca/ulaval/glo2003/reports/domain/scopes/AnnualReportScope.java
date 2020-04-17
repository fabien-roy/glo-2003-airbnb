package ca.ulaval.glo2003.reports.domain.scopes;

import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import java.util.List;
import java.util.stream.Collectors;

public class AnnualReportScope extends ReportScope {

  public AnnualReportScope(ReservationPeriod period) {
    super(period);
  }

  @Override
  public List<ReportPeriod> getReportPeriods() {
    return period.getYears().stream().map(this::createReportPeriod).collect(Collectors.toList());
  }

  private ReportPeriod createReportPeriod(int year) {
    return new ReportPeriod(String.valueOf(year), ReservationPeriod.ofYear(year));
  }
}
