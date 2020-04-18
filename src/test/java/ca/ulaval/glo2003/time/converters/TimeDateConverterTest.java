package ca.ulaval.glo2003.time.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.bookings.exceptions.ArrivalDateInThePastException;
import ca.ulaval.glo2003.bookings.exceptions.InvalidArrivalDateException;
import ca.ulaval.glo2003.time.domain.TimeDate;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TimeDateConverterTest {

  private static TimeDateConverter timeDateConverter;

  @BeforeAll
  public static void setUpConverter() {
    timeDateConverter = new TimeDateConverter();
  }

  @Test
  public void fromString_shouldConvertBookingDate() {
    TimeDate expectedDate = new TimeDate(LocalDate.now());

    TimeDate timeDate = timeDateConverter.fromString(expectedDate.toString());

    assertEquals(expectedDate, timeDate);
  }

  @Test
  public void fromString_withInvalidBookingDate_shouldThrowInvalidArrivalDateException() {
    String invalidArrivalDate = "invalidArrivalDate";

    Assertions.assertThrows(
        InvalidArrivalDateException.class, () -> timeDateConverter.fromString(invalidArrivalDate));
  }

  @Test
  public void fromString_withBookingDateInThePast_shouldThrowArrivalDateInThePastException() {
    String arrivalDateInThePast = LocalDate.now().minusDays(1).toString();

    Assertions.assertThrows(
        ArrivalDateInThePastException.class,
        () -> timeDateConverter.fromString(arrivalDateInThePast));
  }

  @Test
  public void fromString_withoutBookingDate_shouldThrowInvalidArrivalDateException() {
    String nullArrivalDate = null;

    Assertions.assertThrows(
        InvalidArrivalDateException.class, () -> timeDateConverter.fromString(nullArrivalDate));
  }
}
