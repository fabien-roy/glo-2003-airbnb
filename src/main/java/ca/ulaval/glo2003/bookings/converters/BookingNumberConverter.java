package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.bookings.domain.BookingNumber;
import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import java.util.UUID;

public class BookingNumberConverter {

  public BookingNumber fromString(String number) {
    UUID value;

    try {
      value = UUID.fromString(number);
    } catch (IllegalArgumentException exception) {
      throw new BookingNotFoundException(number);
    }

    return new BookingNumber(value);
  }
}
