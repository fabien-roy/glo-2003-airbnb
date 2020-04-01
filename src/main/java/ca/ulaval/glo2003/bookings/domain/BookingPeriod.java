package ca.ulaval.glo2003.bookings.domain;

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
