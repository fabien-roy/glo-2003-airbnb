package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.time.domain.TimeDate;
import ca.ulaval.glo2003.time.domain.TimePeriod;
import ca.ulaval.glo2003.time.domain.Timestamp;
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

  public void setSingleData(ReportPeriodData data) {
    this.data = Collections.singletonList(data);
  }

  public boolean contains(Timestamp timestamp) {
    TimeDate date = new TimeDate(timestamp.toLocalDate());
    return period.contains(date);
  }
}
