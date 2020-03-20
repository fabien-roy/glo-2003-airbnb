package ca.ulaval.glo2003.beds.bookings.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingDateTest {

  private LocalDate date = LocalDate.now();
  private LocalDate otherDate = date.plusDays(1);

  @Test
  void equals_shouldReturnFalse_whenObjectIsNotBookingDate() {
    BookingDate bookingDate = new BookingDate(date);
    Object object = new Object();

    boolean result = bookingDate.equals(object);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    BookingDate bookingDate = new BookingDate(date);
    BookingDate otherBookingDate = new BookingDate(otherDate);

    boolean result = bookingDate.equals(otherBookingDate);

    assertFalse(result);
  }

  @Test
  void equals_shouldReturnTrue_whenValuesAreEqual() {
    BookingDate bookingDate = new BookingDate(date);
    BookingDate otherBookingDate = new BookingDate(date);

    boolean result = bookingDate.equals(otherBookingDate);

    assertTrue(result);
  }
}
