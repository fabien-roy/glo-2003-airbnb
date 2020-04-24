package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.helpers.CalendarHelper.*;
import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateBuilder.aTimeDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo2003.admin.domain.Configuration;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeWeekTest extends TimeCalendarTest {

  @BeforeAll
  public static void setUpConfiguration() {
    Configuration.instance().setLocale(Locale.US);
  }

  private static Stream<Arguments> provideWeekStarts() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 2), firstDateOfWeekOfYear(2020, 2)),
        Arguments.of(buildZonedDateTime(2020, 3), firstDateOfWeekOfYear(2020, 3)),
        Arguments.of(buildZonedDateTime(2020, 6), firstDateOfWeekOfYear(2020, 6)),
        Arguments.of(buildZonedDateTime(2020, 50), firstDateOfWeekOfYear(2020, 50)));
  }

  private static Stream<Arguments> provideWeekEnds() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 2), lastDateOfWeekOfYear(2020, 2)),
        Arguments.of(buildZonedDateTime(2020, 3), lastDateOfWeekOfYear(2020, 3)),
        Arguments.of(buildZonedDateTime(2020, 6), lastDateOfWeekOfYear(2020, 6)),
        Arguments.of(buildZonedDateTime(2020, 50), lastDateOfWeekOfYear(2020, 50)));
  }

  private static Stream<Arguments> provideWeekStrings() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 2), "week#2"),
        Arguments.of(buildZonedDateTime(2020, 3), "week#3"),
        Arguments.of(buildZonedDateTime(2020, 34), "week#34"),
        Arguments.of(buildZonedDateTime(2020, 50), "week#50"));
  }

  private static Stream<Arguments> provideWeekComparisons() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 2), buildZonedDateTime(2020, 4), -2),
        Arguments.of(buildZonedDateTime(2020, 6), buildZonedDateTime(2020, 2), 4),
        Arguments.of(buildZonedDateTime(2020, 50), buildZonedDateTime(2020, 39), 11));
  }

  private static ZonedDateTime buildZonedDateTime(int year, int weekOfYear) {
    return aTimeDate()
        .withYear(year)
        .withWeekOfYear(weekOfYear)
        .build()
        .toTimestamp()
        .toZonedDateTime();
  }

  private static TimeDate firstDateOfWeekOfYear(int year, int weekOfYear) {
    int firstMonthOfWeek = firstMonthOfWeekOfYear(year, weekOfYear) + 1;
    int firstDayOfMonthOfWeekOfYear =
        firstDayOfMonthOfWeekOfYear(year, firstMonthOfWeek - 1, weekOfYear);
    return aTimeDate()
        .withDate(LocalDate.of(year, firstMonthOfWeek, firstDayOfMonthOfWeekOfYear))
        .build();
  }

  private static TimeDate lastDateOfWeekOfYear(int year, int weekOfYear) {
    int lastMonthOfWeekOfYear = lastMonthOfWeekOfYear(year, weekOfYear) + 1;
    int lastDayOfMonthOfWeekOfYear =
        lastDayOfMonthOfWeekOfYear(year, lastMonthOfWeekOfYear - 1, weekOfYear);
    return aTimeDate()
        .withDate(LocalDate.of(year, lastMonthOfWeekOfYear, lastDayOfMonthOfWeekOfYear))
        .build();
  }

  @ParameterizedTest
  @MethodSource("provideWeekStarts")
  public void construct_shouldSetPeriodStartToWeekStart(ZonedDateTime date, TimeDate monthStart) {
    TimeWeek week = new TimeWeek(date);

    assertEquals(monthStart, week.toPeriod().getStart());
  }

  @ParameterizedTest
  @MethodSource("provideWeekEnds")
  public void construct_shouldSetPeriodStartToWeekEnd(ZonedDateTime date, TimeDate monthEnd) {
    TimeWeek timeWeek = new TimeWeek(date);

    assertEquals(monthEnd, timeWeek.toPeriod().getEnd());
  }

  @ParameterizedTest
  @MethodSource("provideWeekStrings")
  public void toString_shouldReturnWeekToString(ZonedDateTime date, String weekString) {
    TimeWeek week = new TimeWeek(date);

    assertEquals(weekString, week.toString());
  }

  @ParameterizedTest
  @MethodSource("provideWeekComparisons")
  public void compareTo_shouldReturnDifferenceOfWeeks(
      ZonedDateTime date, ZonedDateTime otherDate, int difference) {
    TimeWeek month = new TimeWeek(date);
    TimeWeek otherWeek = new TimeWeek(otherDate);

    int comparison = month.compareTo(otherWeek);

    assertEquals(difference, comparison);
  }
}
