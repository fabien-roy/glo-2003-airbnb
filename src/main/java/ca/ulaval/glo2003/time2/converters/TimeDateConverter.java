package ca.ulaval.glo2003.time2.converters;

import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.time2.domain.TimeDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeDateConverter {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

  public TimeDate fromString(String bookingDate) {
    LocalDate value;

    try {
      value = LocalDate.parse(bookingDate, formatter);
    } catch (Exception e) {
      throw new InvalidArrivalDateException();
    }

    if (value.isBefore(LocalDate.now())) throw new ArrivalDateInThePastException();

    return new TimeDate(value);
  }
}
