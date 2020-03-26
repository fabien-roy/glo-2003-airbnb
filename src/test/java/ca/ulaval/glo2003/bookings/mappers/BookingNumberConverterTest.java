package ca.ulaval.glo2003.bookings.mappers;

import static ca.ulaval.glo2003.bookings.domain.helpers.BookingObjectMother.createBookingNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookingNumberConverterTest {

  private static BookingNumberConverter bookingNumberConverter;

  private UUID bookingNumber = createBookingNumber();

  @BeforeAll
  public static void setUpConverter() {
    bookingNumberConverter = new BookingNumberConverter();
  }

  @Test
  public void fromString_shouldConvertBookingNumber() {
    UUID actualBookingNumber = bookingNumberConverter.fromString(bookingNumber.toString());

    assertEquals(bookingNumber, actualBookingNumber);
  }

  @Test
  public void fromString_withInvalidNumber_shouldThrowBookingNotFoundException() {
    String invalidNumber = "invalidNumber";

    assertThrows(
        BookingNotFoundException.class, () -> bookingNumberConverter.fromString(invalidNumber));
  }

  @Test
  public void toString_shouldConvertBookingNumber() {
    String actualString = bookingNumberConverter.toString(bookingNumber);

    assertEquals(bookingNumber.toString(), actualString);
  }
}
