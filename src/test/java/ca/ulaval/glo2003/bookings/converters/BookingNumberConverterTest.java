package ca.ulaval.glo2003.bookings.converters;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createBookingNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.bookings.domain.BookingNumber;
import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookingNumberConverterTest {

  private static BookingNumberConverter bookingNumberConverter;

  private BookingNumber bookingNumber = createBookingNumber();

  @BeforeAll
  public static void setUpConverter() {
    bookingNumberConverter = new BookingNumberConverter();
  }

  @Test
  public void fromString_shouldConvertBookingNumber() {
    BookingNumber actualBookingNumber = bookingNumberConverter.fromString(bookingNumber.toString());

    assertEquals(bookingNumber, actualBookingNumber);
  }

  @Test
  public void fromString_withInvalidNumber_shouldThrowBookingNotFoundException() {
    String invalidNumber = "invalidNumber";

    assertThrows(
        BookingNotFoundException.class, () -> bookingNumberConverter.fromString(invalidNumber));
  }
}
