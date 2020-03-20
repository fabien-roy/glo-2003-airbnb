package ca.ulaval.glo2003.beds.bookings.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BookingDateTest {

  private LocalDate date = LocalDate.now();
  private LocalDate otherDate = date.plusDays(1);

  @Test
  public void construct_withoutParameter_shouldSetValueToNow() {
    LocalDate now = LocalDate.now();

    BookingDate bookingDate = new BookingDate();

    assertEquals(now, bookingDate.getValue());
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingDate() {
    BookingDate bookingDate = new BookingDate(date);
    Object object = new Object();

    boolean result = bookingDate.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    BookingDate bookingDate = new BookingDate(date);
    BookingDate otherBookingDate = new BookingDate(otherDate);

    boolean result = bookingDate.equals(otherBookingDate);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    BookingDate bookingDate = new BookingDate(date);
    BookingDate otherBookingDate = new BookingDate(date);

    boolean result = bookingDate.equals(otherBookingDate);

    assertTrue(result);
  }
}
