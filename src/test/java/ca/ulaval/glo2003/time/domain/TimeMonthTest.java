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

class TimeMonthTest extends TimeCalendarTest {

  private static Stream<Arguments> provideMonthStarts() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 2), firstDateOfMonth(2020, 2)),
        Arguments.of(buildZonedDateTime(2020, 6), firstDateOfMonth(2020, 6)),
        Arguments.of(buildZonedDateTime(1937, 11), firstDateOfMonth(1937, 11)),
        Arguments.of(buildZonedDateTime(2034, 1), firstDateOfMonth(2034, 1)),
        Arguments.of(buildZonedDateTime(2034, 12), firstDateOfMonth(2034, 12)));
  }

  private static Stream<Arguments> provideMonthEnds() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 2), lastDateOfMonth(2020, 2)),
        Arguments.of(buildZonedDateTime(2020, 6), lastDateOfMonth(2020, 6)),
        Arguments.of(buildZonedDateTime(1937, 11), lastDateOfMonth(1937, 11)),
        Arguments.of(buildZonedDateTime(2034, 1), lastDateOfMonth(2034, 1)),
        Arguments.of(buildZonedDateTime(2034, 12), lastDateOfMonth(2034, 12)));
  }

  private static Stream<Arguments> provideMonthStrings() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 1), "january"),
        Arguments.of(buildZonedDateTime(2020, 2), "february"),
        Arguments.of(buildZonedDateTime(2020, 3), "march"),
        Arguments.of(buildZonedDateTime(2020, 4), "april"),
        Arguments.of(buildZonedDateTime(2020, 5), "may"),
        Arguments.of(buildZonedDateTime(2020, 6), "june"),
        Arguments.of(buildZonedDateTime(2020, 7), "july"),
        Arguments.of(buildZonedDateTime(2020, 8), "august"),
        Arguments.of(buildZonedDateTime(2020, 9), "september"),
        Arguments.of(buildZonedDateTime(2020, 10), "october"),
        Arguments.of(buildZonedDateTime(2020, 11), "november"),
        Arguments.of(buildZonedDateTime(2020, 12), "december"));
  }

  private static Stream<Arguments> provideMonthComparisons() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020, 2), buildZonedDateTime(2020, 4), -2),
        Arguments.of(buildZonedDateTime(2020, 6), buildZonedDateTime(2020, 2), 4),
        Arguments.of(buildZonedDateTime(1937, 11), buildZonedDateTime(1938, 3), -4));
  }

  private static ZonedDateTime buildZonedDateTime(int year, int month) {
    return aTimeDate().withYear(year).withMonth(month).build().toTimestamp().toZonedDateTime();
  }

  private static TimeDate firstDateOfMonth(int year, int month) {
    return aTimeDate().withDate(LocalDate.of(year, month, 1)).build();
  }

  private static TimeDate lastDateOfMonth(int year, int month) {
    int lastDayOfMonth = lastDayOfMonth(year, month - 1);
    return aTimeDate().withDate(LocalDate.of(year, month, lastDayOfMonth)).build();
  }

  @ParameterizedTest
  @MethodSource("provideMonthStarts")
  public void construct_shouldSetPeriodStartToMonthStart(ZonedDateTime date, TimeDate monthStart) {
    TimeMonth month = new TimeMonth(date);

    assertEquals(monthStart, month.toPeriod().getStart());
  }

  @ParameterizedTest
  @MethodSource("provideMonthEnds")
  public void construct_shouldSetPeriodStartToMonthEnd(ZonedDateTime date, TimeDate monthEnd) {
    TimeMonth month = new TimeMonth(date);

    assertEquals(monthEnd, month.toPeriod().getEnd());
  }

  @ParameterizedTest
  @MethodSource("provideMonthStrings")
  public void toString_shouldReturnMonthToString(ZonedDateTime date, String monthString) {
    TimeMonth month = new TimeMonth(date);

    assertEquals(monthString, month.toString());
  }

  @ParameterizedTest
  @MethodSource("provideMonthComparisons")
  public void compareTo_shouldReturnDifferenceOfMonths(
      ZonedDateTime date, ZonedDateTime otherDate, int difference) {
    TimeMonth month = new TimeMonth(date);
    TimeMonth otherMonth = new TimeMonth(otherDate);

    int comparison = month.compareTo(otherMonth);

    assertEquals(difference, comparison);
  }
}
