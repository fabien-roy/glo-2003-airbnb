package ca.ulaval.glo2003.bookings.rest.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookingDateMapperTest {

  private static BookingDateMapper bookingDateMapper;

  @BeforeAll
  public static void setUpMapper() {
    bookingDateMapper = new BookingDateMapper();
  }

  @Test
  public void fromString_shouldMapBookingDate() {
    BookingDate expectedDate = new BookingDate(LocalDate.now());
    String requestedDate = expectedDate.getValue().toString();

    BookingDate bookingDate = bookingDateMapper.fromString(requestedDate);

    assertEquals(expectedDate, bookingDate);
  }

  @Test
  public void fromString_withInvalidBookingDate_shouldThrowInvalidArrivalDateException() {
    String invalidArrivalDate = "invalidArrivalDate";

    Assertions.assertThrows(
        InvalidArrivalDateException.class, () -> bookingDateMapper.fromString(invalidArrivalDate));
  }

  @Test
  public void fromString_withBookingDateInThePast_shouldThrowArrivalDateInThePastException() {
    String arrivalDateInThePast = LocalDate.now().minusDays(1).toString();

    Assertions.assertThrows(
        ArrivalDateInThePastException.class,
        () -> bookingDateMapper.fromString(arrivalDateInThePast));
  }

  @Test
  public void fromString_withoutBookingDate_shouldThrowInvalidArrivalDateException() {
    String nullArrivalDate = null;

    Assertions.assertThrows(
        InvalidArrivalDateException.class, () -> bookingDateMapper.fromString(nullArrivalDate));
  }

  @Test
  public void toString_shouldMapBookingDate() {
    BookingDate bookingDate = new BookingDate(LocalDate.now());
    String expectedValue = bookingDate.getValue().toString();

    String value = bookingDateMapper.toString(bookingDate);

    assertEquals(expectedValue, value);
  }
}
