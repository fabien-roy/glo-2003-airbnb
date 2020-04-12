package ca.ulaval.glo2003.bookings.domain;

import java.util.UUID;

public class BookingNumber {

  private UUID value;

  public BookingNumber(UUID value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    BookingNumber bookingNumber = (BookingNumber) object;

    return value.toString().equals(bookingNumber.toString());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
