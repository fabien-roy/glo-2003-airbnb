package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.helpers.TimePeriodBuilder.aTimePeriod;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimePeriodTest {

  private static int numberOfDays = 5;
  private static TimeDate start = TimeDate.now();
  private static TimeDate end = start.plusDays(numberOfDays);
  private static int firstYear = 2020;
  private static int secondYear = 2021;
  private static int firstMonth = 1;
  private static int secondMonth = 2;
  private static int firstQuarter = 2;
  private static int secondQuarter = 3;

  private static TimePeriod period;
  private static TimePeriod samePeriod;
  private static TimePeriod beforePeriod;
  private static TimePeriod afterPeriod;
  private static TimePeriod beforeOverlappingPeriod;
  private static TimePeriod afterOverlappingPeriod;

  private static TimePeriod singleYearPeriod =
      aTimePeriod().withYears(firstYear, firstYear).build();
  private static TimePeriod multipleYearsPeriod =
      aTimePeriod().withYears(firstYear, secondYear).build();
  private static TimePeriod singleMonthPeriod =
      aTimePeriod().withYears(firstYear, firstYear).withMonths(firstMonth, firstMonth).build();
  private static TimePeriod multipleMonthsPeriod =
      aTimePeriod().withYears(firstYear, firstYear).withMonths(firstMonth, secondMonth).build();
  private static TimePeriod multipleYearMonthsPeriod =
      aTimePeriod().withYears(firstYear, secondYear).withMonths(firstMonth, secondMonth).build();
  private static TimePeriod singleQuarterPeriod =
      aTimePeriod()
          .withYears(firstYear, firstYear)
          .withQuarters(firstQuarter, firstQuarter)
          .build();
  private static TimePeriod multipleQuartersPeriod =
      aTimePeriod()
          .withYears(firstYear, firstYear)
          .withQuarters(firstQuarter, secondQuarter)
          .build();
  private static TimePeriod multipleYearQuartersPeriod =
      aTimePeriod()
          .withYears(firstYear, secondYear)
          .withQuarters(firstQuarter, secondQuarter)
          .build();

  @BeforeAll
  public static void setUpPeriodsForOverlapping() {
    TimeDate beforeEnd = start.minusDays(1);
    TimeDate beforeStart = beforeEnd.minusDays(numberOfDays);
    beforePeriod = new TimePeriod(beforeStart, beforeEnd);

    TimeDate afterStart = end.plusDays(1);
    TimeDate afterEnd = afterStart.plusDays(numberOfDays);
    afterPeriod = new TimePeriod(afterStart, afterEnd);

    TimeDate beforeOverlappingEnd = start.plusDays(1);
    TimeDate beforeOverlappingStart = beforeOverlappingEnd.minusDays(numberOfDays);
    beforeOverlappingPeriod = new TimePeriod(beforeOverlappingStart, beforeOverlappingEnd);

    TimeDate afterOverlappingStart = end.minusDays(1);
    TimeDate afterOverlappingEnd = afterOverlappingStart.plusDays(numberOfDays);
    afterOverlappingPeriod = new TimePeriod(afterOverlappingStart, afterOverlappingEnd);
  }

  @BeforeEach
  public void setUpPeriods() {
    period = new TimePeriod(start, end);
    samePeriod = new TimePeriod(start, end);
  }

  @Test
  public void getDates_withASingleDate_shouldGetDate() {
    period = new TimePeriod(start, start);

    List<TimeDate> dates = period.getDates();

    assertEquals(start, dates.get(0));
  }

  @Test
  public void getDates_shouldGetDates() {
    List<TimeDate> dates = period.getDates();

    for (int i = 0; i < numberOfDays; i++) {
      assertEquals(start.plusDays(i), dates.get(i));
    }
  }

  @Test
  public void getYears_withSingleYearPeriod_shouldGetYear() {
    List<TimeCalendar> years = singleYearPeriod.getYears();

    assertEquals(1, years.size());
    assertEquals(firstYear, years.get(0).getYear());
  }

  @Test
  public void getYears_withMultipleYearsPeriod_shouldGetYears() {
    List<TimeCalendar> years = multipleYearsPeriod.getYears();

    assertEquals(2, years.size());
    assertEquals(firstYear, years.get(0).getYear());
    assertEquals(secondYear, years.get(1).getYear());
  }

  @Test
  public void getMonths_withSingleMonthPeriod_shouldGetMonth() {
    List<TimeCalendar> months = singleMonthPeriod.getMonths();

    assertEquals(1, months.size());
    assertEquals(firstYear, months.get(0).getYear());
    assertEquals(firstMonth, months.get(0).getMonth() + 1);
  }

  @Test
  public void getMonths_withMultipleMonthsPeriod_shouldGetMonths() {
    List<TimeCalendar> months = multipleMonthsPeriod.getMonths();

    assertEquals(2, months.size());
    assertEquals(firstYear, months.get(0).getYear());
    assertEquals(firstMonth, months.get(0).getMonth() + 1);
    assertEquals(firstYear, months.get(1).getYear());
    assertEquals(secondMonth, months.get(1).getMonth() + 1);
  }

  @Test
  public void getMonths_withMultipleYearMonthsPeriod_shouldGetMonths() {
    List<TimeCalendar> months = multipleYearMonthsPeriod.getMonths();

    assertEquals(14, months.size());
    assertEquals(firstYear, months.get(0).getYear());
    assertEquals(firstMonth, months.get(0).getMonth() + 1);
    assertEquals(secondYear, months.get(13).getYear());
    assertEquals(secondMonth, months.get(13).getMonth() + 1);
  }

  @Test
  public void getQuarters_withSingleQuarterPeriod_shouldGetQuarter() {
    List<TimeCalendar> quarters = singleQuarterPeriod.getQuarters();

    assertEquals(1, quarters.size());
    assertEquals(firstYear, quarters.get(0).getYear());
    assertEquals(firstQuarter, quarters.get(0).getQuarter());
  }

  @Test
  public void getQuarters_withMultipleQuartersPeriod_shouldGetQuarters() {
    List<TimeCalendar> quarters = multipleQuartersPeriod.getQuarters();

    assertEquals(2, quarters.size());
    assertEquals(firstYear, quarters.get(0).getYear());
    assertEquals(firstQuarter, quarters.get(0).getQuarter());
    assertEquals(firstYear, quarters.get(1).getYear());
    assertEquals(secondQuarter, quarters.get(1).getQuarter());
  }

  @Test
  public void getQuarters_withMultipleYearQuartersPeriod_shouldGetQuarters() {
    List<TimeCalendar> quarters = multipleYearQuartersPeriod.getQuarters();

    assertEquals(6, quarters.size());
    assertEquals(firstYear, quarters.get(0).getYear());
    assertEquals(firstQuarter, quarters.get(0).getQuarter());
    assertEquals(secondYear, quarters.get(5).getYear());
    assertEquals(secondQuarter, quarters.get(5).getQuarter());
  }

  @Test
  public void isOverlapping_withSamePeriod_shouldReturnTrue() {
    boolean result = period.isOverlapping(samePeriod);

    assertTrue(result);
  }

  @Test
  public void isOverlapping_withBeforeOverlappingPeriod_shouldReturnTrue() {
    boolean result = period.isOverlapping(beforeOverlappingPeriod);

    assertTrue(result);
  }

  @Test
  public void isOverlapping_withAfterOverlappingPeriod_shouldReturnTrue() {
    boolean result = period.isOverlapping(afterOverlappingPeriod);

    assertTrue(result);
  }

  @Test
  public void isOverlapping_withBeforePeriod_shouldReturnFalse() {
    boolean result = period.isOverlapping(beforePeriod);

    assertFalse(result);
  }

  @Test
  public void isOverlapping_withAfterPeriod_shouldReturnFalse() {
    boolean result = period.isOverlapping(afterPeriod);

    assertFalse(result);
  }

  @Test
  public void contains_withStartDate_shouldReturnTrue() {
    boolean result = period.contains(period.getStart());

    assertTrue(result);
  }

  @Test
  public void contains_withEndDate_shouldReturnTrue() {
    boolean result = period.contains(period.getEnd());

    assertTrue(result);
  }

  @Test
  public void contains_withContainedDate_shouldReturnTrue() {
    boolean result = period.contains(period.getStart().plusDays(1));

    assertTrue(result);
  }

  @Test
  public void contains_withDateBefore_shouldReturnFalse() {
    boolean result = period.contains(period.getStart().minusDays(1));

    assertFalse(result);
  }

  @Test
  public void contains_withDateAfter_shouldReturnFalse() {
    boolean result = period.contains(period.getEnd().plusDays(1));

    assertFalse(result);
  }
}
