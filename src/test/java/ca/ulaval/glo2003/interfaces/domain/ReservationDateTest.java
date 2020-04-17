package ca.ulaval.glo2003.interfaces.domain;

import static ca.ulaval.glo2003.interfaces.domain.ReservationTimestamp.ZONE_OFFSET;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReservationDateTest {

  private static ReservationDate reservationDate;

  private static LocalDate date = LocalDate.now();
  private static LocalDate otherDate = date.plusDays(1);

  @BeforeAll
  public static void setUpDate() {
    reservationDate = new ReservationDate(date);
  }

  @Test
  public void now_shouldSetValueToNow() {
    LocalDate now = LocalDate.now();

    reservationDate = ReservationDate.now();

    assertEquals(now, reservationDate.toLocalDate());
  }

  @ParameterizedTest
  @ValueSource(ints = {2020, 2030, 1976})
  public void firstDayOfYear_shouldReturnFirstDayOfYear(int year) {
    ReservationDate firstDayOfYear = ReservationDate.firstDayOfYear(year);

    assertEquals(year, firstDayOfYear.toLocalDate().getYear());
    assertEquals(Month.JANUARY, firstDayOfYear.toLocalDate().getMonth());
    assertEquals(1, firstDayOfYear.toLocalDate().getDayOfMonth());
  }

  @ParameterizedTest
  @ValueSource(ints = {2020, 2030, 1976})
  public void lastDayOfYear_shouldReturnLastDayOfYear(int year) {
    ReservationDate lastDayOfYear = ReservationDate.lastDayOfYear(year);

    assertEquals(year, lastDayOfYear.toLocalDate().getYear());
    assertEquals(Month.DECEMBER, lastDayOfYear.toLocalDate().getMonth());
    assertEquals(31, lastDayOfYear.toLocalDate().getDayOfMonth());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void minusDays_shouldReturnBookingDateMinusDays(int days) {
    LocalDate minusDays = date.minusDays(days);

    ReservationDate actualReservationDate = reservationDate.minusDays(days);

    assertEquals(minusDays, actualReservationDate.toLocalDate());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void plusDays_shouldReturnBookingDatePlusDays(int days) {
    LocalDate plusDays = date.plusDays(days);

    ReservationDate actualReservationDate = reservationDate.plusDays(days);

    assertEquals(plusDays, actualReservationDate.toLocalDate());
  }

  @Test
  public void isAfter_withDateBefore_shouldReturnTrue() {
    ReservationDate dateBefore = new ReservationDate(date.minusDays(1));

    boolean result = reservationDate.isAfter(dateBefore);

    assertTrue(result);
  }

  @Test
  public void isAfter_withDateEquals_shouldReturnFalse() {
    ReservationDate dateEqual = new ReservationDate(date);

    boolean result = reservationDate.isAfter(dateEqual);

    assertFalse(result);
  }

  @Test
  public void isAfter_withDateAfter_shouldReturnFalse() {
    ReservationDate dateAfter = new ReservationDate(date.plusDays(1));

    boolean result = reservationDate.isAfter(dateAfter);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateBefore_shouldReturnFalse() {
    ReservationDate dateBefore = new ReservationDate(date.minusDays(1));

    boolean result = reservationDate.isBefore(dateBefore);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateEquals_shouldReturnFalse() {
    ReservationDate dateEqual = new ReservationDate(date);

    boolean result = reservationDate.isBefore(dateEqual);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateAfter_shouldReturnTrue() {
    ReservationDate dateAfter = new ReservationDate(date.plusDays(1));

    boolean result = reservationDate.isBefore(dateAfter);

    assertTrue(result);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void periodTo_shouldReturnPeriodWithThisAsStart(int days) {
    ReservationPeriod period = reservationDate.periodToDays(days);

    assertEquals(reservationDate, period.getStart());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void periodTo_shouldReturnPeriodWithThisInDaysAsEnd(int days) {
    ReservationDate reservationDateInDays = reservationDate.plusDays(days);

    ReservationPeriod period = reservationDate.periodToDays(days);

    assertEquals(reservationDateInDays, period.getEnd());
  }

  @Test
  public void toPeriod_shouldHaveDateAsStart() {
    ReservationPeriod period = reservationDate.toPeriod();

    assertEquals(period.getStart(), reservationDate);
  }

  @Test
  public void toPeriod_shouldHaveDateAsEnd() {
    ReservationPeriod period = reservationDate.toPeriod();

    assertEquals(period.getEnd(), reservationDate);
  }

  @Test
  public void toTimestamp_shouldReturnTimestampWithSameValue() {
    ReservationTimestamp reservationTimestamp = reservationDate.toTimestamp();

    assertEquals(
        reservationDate.toLocalDate(),
        reservationTimestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void toString_shouldReturnValueToString() {
    assertEquals(date.toString(), reservationDate.toString());
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingDate() {
    Object object = new Object();

    boolean result = reservationDate.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    ReservationDate otherReservationDate = new ReservationDate(otherDate);

    boolean result = reservationDate.equals(otherReservationDate);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    ReservationDate otherReservationDate = new ReservationDate(date);

    boolean result = reservationDate.equals(otherReservationDate);

    assertTrue(result);
  }
}
