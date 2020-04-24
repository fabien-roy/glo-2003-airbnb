package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateBuilder.aTimeDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeMonthTest {

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
    int lastDayOfMonth = lastDayOfMonth(year, month);
    return aTimeDate().withDate(LocalDate.of(year, month, lastDayOfMonth)).build();
  }

  private static int lastDayOfMonth(int year, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month);
    return calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
  }

  @ParameterizedTest
  @MethodSource("provideMonthStarts")
  public void construct_shouldSetPeriodStartToMonthStart(ZonedDateTime date, TimeDate monthStart) {
    TimeMonth month = new TimeMonth(date);

    assertEquals(monthStart, month.toPeriod().getStart());
  }

  @ParameterizedTest
  @MethodSource("provideMonthEnds")
  public void construct_shouldSetPeriodStartToMonthEnd(ZonedDateTime date, TimeDate yearEnd) {
    TimeMonth month = new TimeMonth(date);

    assertEquals(yearEnd, month.toPeriod().getEnd());
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
