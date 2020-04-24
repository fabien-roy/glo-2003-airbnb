package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.helpers.CalendarBuilder.aCalendar;
import static ca.ulaval.glo2003.time2.domain.helpers.TimeDateBuilder.aTimeDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeYearTest {

  @ParameterizedTest
  @MethodSource("provideYearStarts")
  public void construct_shouldSetPeriodStartToYearStart(Calendar date, TimeDate yearStart) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearStart, year.getPeriod().getStart());
  }

  private static Stream<Arguments> provideYearStarts() {
    return Stream.of(
        Arguments.of(calendarOfYear(2020), firstDateOfYear(2020)),
        Arguments.of(calendarOfYear(1937), firstDateOfYear(1937)),
        Arguments.of(calendarOfYear(2034), firstDateOfYear(2034)));
  }

  @ParameterizedTest
  @MethodSource("provideYearEnds")
  public void construct_shouldSetPeriodStartToYearEnd(Calendar date, TimeDate yearEnd) {
    TimeYear year = new TimeYear(date);

    assertEquals(yearEnd, year.getPeriod().getEnd());
  }

  private static Stream<Arguments> provideYearEnds() {
    return Stream.of(
        Arguments.of(calendarOfYear(2020), lastDateOfYear(2020)),
        Arguments.of(calendarOfYear(1937), lastDateOfYear(1937)),
        Arguments.of(calendarOfYear(2034), lastDateOfYear(2034)));
  }

  private static Calendar calendarOfYear(int year) {
    return aCalendar().withYear(year).build();
  }

  private static TimeDate firstDateOfYear(int year) {
    return aTimeDate().withDate(LocalDate.of(year, 1, 1)).build();
  }

  private static TimeDate lastDateOfYear(int year) {
    return aTimeDate().withDate(LocalDate.of(year, 12, 31)).build();
  }

  @Test
  public void toString_shouldReturnYearToString() {
    // TODO
  }
}
