package ca.ulaval.glo2003.bookings.domain;

import java.time.LocalDate;

public class BookingDate {

  private LocalDate value;

  public BookingDate(LocalDate value) {
    this.value = value;
  }

  public LocalDate getValue() {
    return value;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    BookingDate bookingDate = (BookingDate) object;

    return value.equals(bookingDate.getValue());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
