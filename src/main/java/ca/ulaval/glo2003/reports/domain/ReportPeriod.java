package ca.ulaval.glo2003.reports.domain;

import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.domain.BookingPeriod;
import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.util.Collections;
import java.util.List;

// TODO : Test ReportPeriod
public class ReportPeriod {

  private final BookingPeriod bookingPeriod;
  private List<ReportPeriodData> data;

  public ReportPeriod(BookingPeriod bookingPeriod) {
    this.bookingPeriod = bookingPeriod;
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
    BookingDate date = new BookingDate(timestamp.toLocalDate());
    return bookingPeriod.contains(date);
  }
}
