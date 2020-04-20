package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import java.util.Collections;
import java.util.List;

public class ReportPeriod {

  private final String name;
  private final TimePeriod period;
  private List<ReportPeriodData> data;

  public ReportPeriod(String name, TimePeriod period) {
    this.name = name;
    this.period = period;
  }

  public String getName() {
    return name;
  }

  public TimePeriod getPeriod() {
    return period;
  }

  public List<ReportPeriodData> getData() {
    return data;
  }

  public void setData(List<ReportPeriodData> data) {
    this.data = data;
  }

  public void setSingleData(List<ReportEvent> events) {
    ReportPeriodData singleData = new ReportPeriodData(events);
    this.data = Collections.singletonList(singleData);
  }

  public boolean contains(TimeDate date) {
    return period.contains(date);
  }
}
