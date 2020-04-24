package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.Timestamp.ZONE_OFFSET;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeDateTest {

  private static TimeDate timeDate;
  private static TimeDate otherTimeDate;

  private static LocalDate date = LocalDate.now();
  private static LocalDate otherDate = date.plusDays(1);

  @BeforeAll
  public static void setUpDate() {
    timeDate = new TimeDate(date);
    otherTimeDate = new TimeDate(otherDate);
  }

  @Test
  public void now_shouldSetValueToNow() {
    LocalDate now = LocalDate.now();

    timeDate = TimeDate.now();

    assertEquals(now, timeDate.toLocalDate());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void minusDays_shouldReturnBookingDateMinusDays(int days) {
    LocalDate minusDays = date.minusDays(days);

    TimeDate actualDate = timeDate.minusDays(days);

    assertEquals(minusDays, actualDate.toLocalDate());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void plusDays_shouldReturnBookingDatePlusDays(int days) {
    LocalDate plusDays = date.plusDays(days);

    TimeDate actualDate = timeDate.plusDays(days);

    assertEquals(plusDays, actualDate.toLocalDate());
  }

  @Test
  public void isBefore_withDateBefore_shouldReturnFalse() {
    TimeDate dateBefore = new TimeDate(date.minusDays(1));

    boolean result = timeDate.isBefore(dateBefore);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateEquals_shouldReturnFalse() {
    TimeDate dateEqual = new TimeDate(date);

    boolean result = timeDate.isBefore(dateEqual);

    assertFalse(result);
  }

  @Test
  public void isBefore_withDateAfter_shouldReturnTrue() {
    TimeDate dateAfter = new TimeDate(date.plusDays(1));

    boolean result = timeDate.isBefore(dateAfter);

    assertTrue(result);
  }

  @Test
  public void isAfter_withDateBefore_shouldReturnTrue() {
    TimeDate dateBefore = new TimeDate(date.minusDays(1));

    boolean result = timeDate.isAfter(dateBefore);

    assertTrue(result);
  }

  @Test
  public void isAfter_withDateEquals_shouldReturnFalse() {
    TimeDate dateEqual = new TimeDate(date);

    boolean result = timeDate.isAfter(dateEqual);

    assertFalse(result);
  }

  @Test
  public void isAfter_withDateAfter_shouldReturnFalse() {
    TimeDate dateAfter = new TimeDate(date.plusDays(1));

    boolean result = timeDate.isAfter(dateAfter);

    assertFalse(result);
  }

  @Test
  public void toPeriod_shouldHaveDateAsStart() {
    TimePeriod period = timeDate.toPeriod();

    assertEquals(period.getStart(), timeDate);
  }

  @Test
  public void toPeriod_shouldHaveDateAsEnd() {
    TimePeriod period = timeDate.toPeriod();

    assertEquals(period.getEnd(), timeDate);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void toPeriod_withDays_shouldReturnPeriodWithThisAsStart(int days) {
    TimePeriod period = timeDate.toPeriod(days);

    assertEquals(timeDate, period.getStart());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5})
  public void toPeriod_withDays_shouldReturnPeriodWithThisInDaysAsEnd(int days) {
    TimeDate timeDateInDays = timeDate.plusDays(days);

    TimePeriod period = timeDate.toPeriod(days);

    assertEquals(timeDateInDays, period.getEnd());
  }

  @Test
  public void toLocalDate_shouldReturnValueToLocalDate() {
    assertEquals(date, timeDate.toLocalDate());
  }

  @Test
  public void toTimestamp_shouldReturnTimestampWithSameValue() {
    Timestamp timestamp = timeDate.toTimestamp();

    assertEquals(timeDate.toLocalDate(), timestamp.toInstant().atOffset(ZONE_OFFSET).toLocalDate());
  }

  @Test
  public void toString_shouldReturnValueToString() {
    assertEquals(date.toString(), timeDate.toString());
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingDate() {
    Object object = new Object();

    boolean result = timeDate.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    boolean result = timeDate.equals(otherTimeDate);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    TimeDate otherTimeDate = new TimeDate(date);

    boolean result = timeDate.equals(otherTimeDate);

    assertTrue(result);
  }
}
