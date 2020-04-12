package ca.ulaval.glo2003.bookings.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookingDateConverterTest {

  private static BookingDateConverter bookingDateConverter;

  @BeforeAll
  public static void setUpConverter() {
    bookingDateConverter = new BookingDateConverter();
  }

  @Test
  public void fromString_shouldConvertBookingDate() {
    BookingDate expectedDate = new BookingDate(LocalDate.now());

    BookingDate bookingDate = bookingDateConverter.fromString(expectedDate.toString());

    assertEquals(expectedDate, bookingDate);
  }

  @Test
  public void fromString_withInvalidBookingDate_shouldThrowInvalidArrivalDateException() {
    String invalidArrivalDate = "invalidArrivalDate";

    Assertions.assertThrows(
        InvalidArrivalDateException.class,
        () -> bookingDateConverter.fromString(invalidArrivalDate));
  }

  @Test
  public void fromString_withBookingDateInThePast_shouldThrowArrivalDateInThePastException() {
    String arrivalDateInThePast = LocalDate.now().minusDays(1).toString();

    Assertions.assertThrows(
        ArrivalDateInThePastException.class,
        () -> bookingDateConverter.fromString(arrivalDateInThePast));
  }

  @Test
  public void fromString_withoutBookingDate_shouldThrowInvalidArrivalDateException() {
    String nullArrivalDate = null;

    Assertions.assertThrows(
        InvalidArrivalDateException.class, () -> bookingDateConverter.fromString(nullArrivalDate));
  }
}
