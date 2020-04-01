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

  // TODO : Test
  public boolean isOverlapping(BookingPeriod other) {
    return !(start.isAfter(other.getEnd()) || end.isBefore(other.getStart()));
  }

  // TODO : Test
  public boolean isOverlapping(BookingDate otherDate, int numberOfNights) {
    return isOverlapping(new BookingPeriod(otherDate, otherDate.plusDays(numberOfNights)));
  }
}
