package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import ca.ulaval.glo2003.beds.bookings.domain.BookingDate;
import ca.ulaval.glo2003.beds.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.beds.bookings.exceptions.InvalidArrivalDateException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingDateMapper {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

  public BookingDate fromString(String bookingDate) {
    if (bookingDate == null) throw new InvalidArrivalDateException();

    LocalDate value;

    try {
      value = LocalDate.parse(bookingDate, formatter);
    } catch (DateTimeParseException e) {
      throw new InvalidArrivalDateException();
    }

    if (value.isBefore(LocalDate.now())) throw new ArrivalDateInThePastException();

    return new BookingDate(value);
  }

  public String toString(BookingDate bookingDate) {
    return bookingDate.getValue().toString();
  }
}
