package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp;
import java.util.Collections;
import java.util.List;

// TODO : Test ReportPeriod
public class ReportPeriod {

  private final String name;
  private final ReservationPeriod period;
  private List<ReportPeriodData> data;

  public ReportPeriod(String name, ReservationPeriod period) {
    this.name = name;
    this.period = period;
  }

  public String getName() {
    return name;
  }

  public ReservationPeriod getPeriod() {
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

  public boolean contains(ReservationTimestamp reservationTimestamp) {
    ReservationDate date = new ReservationDate(reservationTimestamp.toLocalDate());
    return period.contains(date);
  }
}
