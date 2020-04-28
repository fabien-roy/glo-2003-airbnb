package ca.ulaval.glo2003.time.domain;

import static ca.ulaval.glo2003.time.domain.helpers.CalendarHelper.lastDayOfMonth;
import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeQuarterTest extends TimeCalendarTest {

  private static Stream<Arguments> provideQuarterStarts() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 1), firstDateOfQuarter(2020, 1)),
        Arguments.of(buildZonedDateTime(2020, 3), firstDateOfQuarter(2020, 3)),
        Arguments.of(buildZonedDateTime(1937, 2), firstDateOfQuarter(1937, 2)),
        Arguments.of(buildZonedDateTime(2034, 4), firstDateOfQuarter(2034, 4)),
        Arguments.of(buildZonedDateTime(2034, 1), firstDateOfQuarter(2034, 1)));
  }

  private static Stream<Arguments> provideQuarterEnds() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 1), lastDateOfQuarter(2020, 1)),
        Arguments.of(buildZonedDateTime(2020, 3), lastDateOfQuarter(2020, 3)),
        Arguments.of(buildZonedDateTime(1937, 2), lastDateOfQuarter(1937, 2)),
        Arguments.of(buildZonedDateTime(2034, 4), lastDateOfQuarter(2034, 4)),
        Arguments.of(buildZonedDateTime(2034, 1), lastDateOfQuarter(2034, 1)));
  }

  private static Stream<Arguments> provideQuarterStrings() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 1), "q1"),
        Arguments.of(buildZonedDateTime(2020, 2), "q2"),
        Arguments.of(buildZonedDateTime(2020, 3), "q3"),
        Arguments.of(buildZonedDateTime(2020, 4), "q4"));
  }

  private static Stream<Arguments> provideQuarterComparisons() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 1), buildZonedDateTime(2020, 4), -3),
        Arguments.of(buildZonedDateTime(2020, 3), buildZonedDateTime(2020, 2), 1),
        Arguments.of(buildZonedDateTime(1937, 1), buildZonedDateTime(1938, 3), -6));
  }

  private static ZonedDateTime buildZonedDateTime(int year, int quarter) {
    return aTimeDate().withYear(year).withQuarter(quarter).build().toTimestamp().toZonedDateTime();
  }

  private static TimeDate firstDateOfQuarter(int year, int quarter) {
    return aTimeDate().withDate(LocalDate.of(year, quarter * 3 - 2, 1)).build();
  }

  private static TimeDate lastDateOfQuarter(int year, int quarter) {
    int month = quarter * 3;
    int lastDayOfMonth = lastDayOfMonth(year, month - 1);
    return aTimeDate().withDate(LocalDate.of(year, month, lastDayOfMonth)).build();
  }

  @ParameterizedTest
  @MethodSource("provideQuarterStarts")
  public void construct_shouldSetPeriodStartToQuarterStart(
      ZonedDateTime date, TimeDate quarterStart) {
    TimeQuarter quarter = new TimeQuarter(date);

    assertEquals(quarterStart, quarter.toPeriod().getStart());
  }

  @ParameterizedTest
  @MethodSource("provideQuarterEnds")
  public void construct_shouldSetPeriodEndToQuarterEnd(ZonedDateTime date, TimeDate quarterEnd) {
    TimeQuarter quarter = new TimeQuarter(date);

    assertEquals(quarterEnd, quarter.toPeriod().getEnd());
  }

  @ParameterizedTest
  @MethodSource("provideQuarterStrings")
  public void toString_shouldReturnQuarterToString(ZonedDateTime date, String quarterString) {
    TimeQuarter quarter = new TimeQuarter(date);

    assertEquals(quarterString, quarter.toString());
  }

  @ParameterizedTest
  @MethodSource("provideQuarterComparisons")
  public void compareTo_shouldReturnDifferenceOfQuarters(
      ZonedDateTime date, ZonedDateTime otherDate, int difference) {
    TimeQuarter quarter = new TimeQuarter(date);
    TimeQuarter otherQuarter = new TimeQuarter(otherDate);

    int comparison = quarter.compareTo(otherQuarter);

    assertEquals(difference, comparison);
  }
}
