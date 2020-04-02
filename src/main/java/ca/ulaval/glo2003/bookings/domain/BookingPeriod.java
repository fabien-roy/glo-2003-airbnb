package ca.ulaval.glo2003.bookings.domain;

import java.util.ArrayList;
import java.util.List;

public class BookingPeriod {

  private BookingDate start;
  private BookingDate end;

  public BookingPeriod(BookingDate start, BookingDate end) {
    this.start = start;
    this.end = end;
  }

  public BookingDate getStart() {
    return start;
  }

  public BookingDate getEnd() {
    return end;
  }

  public boolean isOverlapping(BookingPeriod other) {
    return !(start.isAfter(other.getEnd()) || end.isBefore(other.getStart()));
  }

  public List<BookingDate> getDates() {
    List<BookingDate> dates = new ArrayList<>();

    for (BookingDate date = start; date.isBefore(end); date = date.plusDays(1)) {
      dates.add(date);
    }

    return dates;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    BookingPeriod bookingPeriod = (BookingPeriod) object;

    return start.equals(bookingPeriod.getStart()) && end.equals(bookingPeriod.getEnd());
  }

  @Override
  public int hashCode() {
    return start.hashCode() + end.hashCode();
  }
}
