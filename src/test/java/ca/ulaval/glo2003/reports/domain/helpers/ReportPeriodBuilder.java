package ca.ulaval.glo2003.reports.domain.helpers;

import static ca.ulaval.glo2003.reports.domain.helpers.ReportPeriodObjectMother.createPeriodName;
import static ca.ulaval.glo2003.time.domain.helpers.TimePeriodBuilder.aTimePeriod;

import ca.ulaval.glo2003.reports.domain.ReportPeriod;
import ca.ulaval.glo2003.time.domain.TimePeriod;

public class ReportPeriodBuilder {

  private String name = createPeriodName();
  private TimePeriod period = aTimePeriod().build();

  public static ReportPeriodBuilder aReportPeriod() {
    return new ReportPeriodBuilder();
  }

  public ReportPeriod build() {
    return new ReportPeriod(name, period);
  }
}
