package ca.ulaval.glo2003.bookings.converters;

import ca.ulaval.glo2003.bookings.domain.BookingDate;
import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingDateConverter {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

  public BookingDate fromString(String bookingDate) {
    LocalDate value;

    try {
      value = LocalDate.parse(bookingDate, formatter);
    } catch (Exception e) {
      throw new InvalidArrivalDateException();
    }

    if (value.isBefore(LocalDate.now())) throw new ArrivalDateInThePastException();

    return new BookingDate(value);
  }

  public String toString(BookingDate bookingDate) {
    return bookingDate.getValue().toString();
  }
}
