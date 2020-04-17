package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.interfaces.domain.ReservationDate;
import ca.ulaval.glo2003.interfaces.domain.ReservationPeriod;
import ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp;
import java.util.Collections;
import java.util.List;

public class ReportPeriod {

  private final ReservationPeriod reservationPeriod;
  private List<ReportPeriodData> data;

  public ReportPeriod(ReservationPeriod reservationPeriod) {
    this.reservationPeriod = reservationPeriod;
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
    return reservationPeriod.contains(date);
  }
}
