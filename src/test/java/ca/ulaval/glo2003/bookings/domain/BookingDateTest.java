package ca.ulaval.glo2003.bookings.domain;

import static ca.ulaval.glo2003.transactions.domain.Timestamp.ZONE_OFFSET;
import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo2003.transactions.domain.Timestamp;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BookingDateTest {

  private static BookingDate bookingDate;

  private static LocalDate date = LocalDate.now();
  private static LocalDate otherDate = date.plusDays(1);

  @BeforeAll
  public static void setUpDate() {
    bookingDate = new BookingDate(date);
  }

  @Test
  public void now_shouldSetValueToNow() {
    LocalDate now = LocalDate.now();

    bookingDate = BookingDate.now();

    assertEquals(now, bookingDate.toLocalDate());
  }

  @ParameterizedTest
  @ValueSource(ints = {2020, 2030, 1976})
  public void firstDayOfYear_shouldReturnFirstDayOfYear(int year) {
    BookingDate firstDayOfYear = BookingDate.firstDayOfYear(year);

    assertEquals(year, firstDayOfYear.toLocalDate().getYear());
    assertEquals(Month.JANUARY, firstDayOfYear.toLocalDate().getMonth());
    assertEquals(1, firstDayOfYear.toLocalDate().getDayOfMonth());
  }

  @ParameterizedTest
  @ValueSource(ints = {2020, 2030, 1976})
  public void lastDayOfYear_shouldReturnLastDayOfYear(int year) {
    BookingDate lastDayOfYear = BookingDate.lastDayOfYear(year);

    assertEquals(year, lastDayOfYear.toLocalDate().getYear());
    assertEquals(Month.DECEMBER, lastDayOfYear.toLocalDate().getMonth());
    assertEquals(31, lastDayOfYear.toLocalDate().getDayOfMonth());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void minusDays_shouldReturnBookingDateMinusDays(int days) {
    LocalDate minusDays = date.minusDays(days);

    BookingDate actualBookingDate = bookingDate.minusDays(days);

    assertEquals(minusDays, actualBookingDate.toLocalDate());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void plusDays_shouldReturnBookingDatePlusDays(int days) {
    LocalDate plusDays = date.plusDays(days);

    BookingDate actualBookingDate = bookingDate.plusDays(days);

    assertEquals(plusDays, actualBookingDate.toLocalDate());
  }

  @Test
  public void isAfter_withDateBefore_shouldReturnTrue() {
    BookingDate dateBefore = new BookingDate(date.minusDays(1));

    boolean result = bookingDate.isAfter(dateBefore);

    assertTrue(result);
  }

  @Test
  public void isAfter_withDateEquals_shouldReturnFalse() {
    BookingDate dateEqual = new BookingDate(date);

    boolean result = bookingDate.isAfter(dateEqual);

    assertFalse(result);
  }

  @Test
  public void isAfter_withDateAfter_shouldReturnFalse() {
    BookingDate dateAfter = new BookingDate(date.plusDays(1));

    boolean result = bookingDate.isAfter(dateAfter);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateBefore_shouldReturnFalse() {
    BookingDate dateBefore = new BookingDate(date.minusDays(1));

    boolean result = bookingDate.isBefore(dateBefore);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateEquals_shouldReturnFalse() {
    BookingDate dateEqual = new BookingDate(date);

    boolean result = bookingDate.isBefore(dateEqual);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateAfter_shouldReturnTrue() {
    BookingDate dateAfter = new BookingDate(date.plusDays(1));

    boolean result = bookingDate.isBefore(dateAfter);

    assertTrue(result);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void periodTo_shouldReturnPeriodWithThisAsStart(int days) {
    BookingPeriod period = bookingDate.periodToDays(days);

    assertEquals(bookingDate, period.getStart());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void periodTo_shouldReturnPeriodWithThisInDaysAsEnd(int days) {
    BookingDate bookingDateInDays = bookingDate.plusDays(days);

    BookingPeriod period = bookingDate.periodToDays(days);

    assertEquals(bookingDateInDays, period.getEnd());
  }

  @Test
  public void toTimestamp_shouldReturnTimestampWithSameValue() {
    Timestamp timestamp = bookingDate.toTimestamp();

    assertEquals(
        bookingDate.toLocalDate(), timestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void toString_shouldReturnValueToString() {
    assertEquals(date.toString(), bookingDate.toString());
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingDate() {
    Object object = new Object();

    boolean result = bookingDate.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    BookingDate otherBookingDate = new BookingDate(otherDate);

    boolean result = bookingDate.equals(otherBookingDate);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    BookingDate otherBookingDate = new BookingDate(date);

    boolean result = bookingDate.equals(otherBookingDate);

    assertTrue(result);
  }
}
