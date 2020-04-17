package ca.ulaval.glo2003.bookings.domain;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.time.LocalDate;
import java.time.Month;

public class BookingDate {

  private LocalDate value;

  public static BookingDate now() {
    return new BookingDate(LocalDate.now());
  }

  public static BookingDate firstDayOfYear(int year) {
    return new BookingDate(LocalDate.of(year, Month.JANUARY, 1));
  }

  public static BookingDate lastDayOfYear(int year) {
    return new BookingDate(LocalDate.of(year, Month.DECEMBER, 31));
  }

  public BookingDate(LocalDate value) {
    this.value = value;
  }

  public BookingDate minusDays(int days) {
    return new BookingDate(value.minusDays(days));
  }

  public BookingDate plusDays(int days) {
    return new BookingDate(value.plusDays(days));
  }

  public boolean isBefore(BookingDate other) {
    return value.isBefore(other.toLocalDate());
  }

  public boolean isAfter(BookingDate other) {
    return value.isAfter(other.toLocalDate());
  }

  public BookingPeriod periodToDays(int days) {
    return periodTo(plusDays(days));
  }

  private BookingPeriod periodTo(BookingDate other) {
    return new BookingPeriod(this, other);
  }

  public BookingPeriod toPeriod() {
    return new BookingPeriod(this, this);
  }

  public Timestamp toTimestamp() {
    return new Timestamp(value);
  }

  public LocalDate toLocalDate() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    BookingDate bookingDate = (BookingDate) object;

    return value.equals(bookingDate.toLocalDate());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
