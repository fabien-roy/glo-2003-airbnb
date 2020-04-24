package ca.ulaval.glo2003.time2.domain;

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

class TimeYearTest {

  @BeforeAll
  public static void setUpConfiguration() {
    Configuration.instance().setLocale(Locale.US);
  }

  private static Stream<Arguments> provideYearStarts() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020), firstDateOfYear(2020)),
        Arguments.of(buildZonedDateTime(1937), firstDateOfYear(1937)),
        Arguments.of(buildZonedDateTime(2034), firstDateOfYear(2034)));
  }

  private static Stream<Arguments> provideYearEnds() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020), lastDateOfYear(2020)),
        Arguments.of(buildZonedDateTime(1937), lastDateOfYear(1937)),
        Arguments.of(buildZonedDateTime(2034), lastDateOfYear(2034)));
  }

  private static Stream<Arguments> provideYearStrings() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2020), "2020"),
        Arguments.of(buildZonedDateTime(1937), "1937"),
        Arguments.of(buildZonedDateTime(2034), "2034"));
  }

  private static Stream<Arguments> provideYearComparisons() {
    return Stream.of(
        Arguments.of(buildZonedDateTime(2023), buildZonedDateTime(2020), 3),
        Arguments.of(buildZonedDateTime(2000), buildZonedDateTime(1937), 63),
        Arguments.of(buildZonedDateTime(2000), buildZonedDateTime(2000), 0),
        Arguments.of(buildZonedDateTime(2000), buildZonedDateTime(2034), -34));
  }

  private static ZonedDateTime buildZonedDateTime(int year) {
    return aTimeDate().withYear(year).build().toTimestamp().toZonedDateTime();
  }

  private static TimeDate firstDateOfYear(int year) {
    return aTimeDate().withDate(LocalDate.of(year, 1, 1)).build();
  }

  private static TimeDate lastDateOfYear(int year) {
    return aTimeDate().withDate(LocalDate.of(year, 12, 31)).build();
  }

  @ParameterizedTest
  @MethodSource("provideYearStarts")
  public void construct_shouldSetPeriodStartToYearStart(ZonedDateTime date, TimeDate yearStart) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearStart, year.toPeriod().getStart());
  }

  @ParameterizedTest
  @MethodSource("provideYearEnds")
  public void construct_shouldSetPeriodStartToYearEnd(ZonedDateTime date, TimeDate yearEnd) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearEnd, year.toPeriod().getEnd());
  }

  @ParameterizedTest
  @MethodSource("provideYearStrings")
  public void toString_shouldReturnYearToString(ZonedDateTime date, String yearString) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearString, year.toString());
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
}
