package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.bookings.exceptions.BookingNotFoundException;
import java.util.UUID;

public class BookingNumberConverter {

  public UUID fromString(String number) {
    try {
      return UUID.fromString(number);
    } catch (IllegalArgumentException exception) {
      throw new BookingNotFoundException(number);
    }
  }

  public String toString(UUID number) {
    return number.toString();
  }
}
