package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.time2.domain.helpers.ZonedDateTimeBuilder.aZonedDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeYearTest {

  @ParameterizedTest
  @MethodSource("provideYearStarts")
  public void construct_shouldSetPeriodStartToYearStart(ZonedDateTime date, TimeDate yearStart) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearStart, year.toPeriod().getStart());
  }

  private static Stream<Arguments> provideYearStarts() {
    return Stream.of(
        Arguments.of(zonedDateTimeOfYear(2020), firstDateOfYear(2020)),
        Arguments.of(zonedDateTimeOfYear(1937), firstDateOfYear(1937)),
        Arguments.of(zonedDateTimeOfYear(2034), firstDateOfYear(2034)));
  }

  @ParameterizedTest
  @MethodSource("provideYearEnds")
  public void construct_shouldSetPeriodStartToYearEnd(ZonedDateTime date, TimeDate yearEnd) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearEnd, year.toPeriod().getEnd());
  }

  private static Stream<Arguments> provideYearEnds() {
    return Stream.of(
        Arguments.of(zonedDateTimeOfYear(2020), lastDateOfYear(2020)),
        Arguments.of(zonedDateTimeOfYear(1937), lastDateOfYear(1937)),
        Arguments.of(zonedDateTimeOfYear(2034), lastDateOfYear(2034)));
  }

  private static ZonedDateTime zonedDateTimeOfYear(int year) {
    return aZonedDateTime().withYear(year).build();
  }

  private static TimeDate firstDateOfYear(int year) {
    return aTimeDate().withDate(LocalDate.of(year, 1, 1)).build();
  }

  private static TimeDate lastDateOfYear(int year) {
    return aTimeDate().withDate(LocalDate.of(year, 12, 31)).build();
  }

  @ParameterizedTest
  @MethodSource("provideYearStrings")
  public void toString_shouldReturnYearToString(ZonedDateTime date, String yearString) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearString, year.toString());
  }

  private static Stream<Arguments> provideYearStrings() {
    return Stream.of(
        Arguments.of(zonedDateTimeOfYear(2020), "2020"),
        Arguments.of(zonedDateTimeOfYear(1937), "1937"),
        Arguments.of(zonedDateTimeOfYear(2034), "2034"));
  }

  @ParameterizedTest
  @MethodSource("provideYearComparisons")
  public void compareTo_shouldReturnDifferenceOfYears(
      ZonedDateTime date, ZonedDateTime otherDate, int difference) {
    TimeYear year = new TimeYear(date);
    TimeYear otherYear = new TimeYear(otherDate);

    int comparison = year.compareTo(otherYear);

    assertEquals(difference, comparison);
  }

  private static Stream<Arguments> provideYearComparisons() {
    return Stream.of(
        Arguments.of(zonedDateTimeOfYear(2023), zonedDateTimeOfYear(2020), 3),
        Arguments.of(zonedDateTimeOfYear(2000), zonedDateTimeOfYear(1937), 63),
        Arguments.of(zonedDateTimeOfYear(2000), zonedDateTimeOfYear(2000), 0),
        Arguments.of(zonedDateTimeOfYear(2000), zonedDateTimeOfYear(2034), -34));
  }
}
