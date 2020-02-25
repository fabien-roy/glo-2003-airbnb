package ca.ulaval.glo2003.beds.bookings.rest.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class BookingDateMapper {

  public static boolean isNotAValidDate(String date) {
    return !DATE_PATTERN.matcher(date).matches();
  }

  public static LocalDate fromString(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(date, formatter);
  }

  private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

  public static boolean isBeforeToday(String dateToCompare) {
    LocalDate date = fromString(dateToCompare);
    return date.isBefore(LocalDate.now());
  }
}
