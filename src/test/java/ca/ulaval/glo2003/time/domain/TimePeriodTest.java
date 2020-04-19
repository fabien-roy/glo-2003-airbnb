package ca.ulaval.glo2003.time.domain;

import static ca.ulaval.glo2003.time.domain.helpers.TimeMonthBuilder.aTimeMonth;
import static ca.ulaval.glo2003.time.domain.helpers.TimePeriodBuilder.aTimePeriod;
import static ca.ulaval.glo2003.time.domain.helpers.TimeQuarterBuilder.aTimeQuarter;
import static ca.ulaval.glo2003.time.domain.helpers.TimeWeekBuilder.aTimeWeek;
import static ca.ulaval.glo2003.time.domain.helpers.TimeYearBuilder.aTimeYear;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimePeriodTest {

  private static int numberOfDays = 5;
  private static TimeDate start = TimeDate.now();
  private static TimeDate end = start.plusDays(numberOfDays);
  private static TimeYear firstYear = aTimeYear().build();
  private static TimeYear secondYear = firstYear.plusYears(1);
  private static TimeQuarter firstQuarter = aTimeQuarter().withYear(firstYear).build();
  private static TimeQuarter secondQuarter = firstQuarter.plusQuarters(1);
  private static TimeMonth firstMonth = aTimeMonth().withQuarter(firstQuarter).build();
  private static TimeMonth secondMonth = firstMonth.plusMonths(1);
  private static TimeWeek firstWeek = aTimeWeek().withYear(firstYear).build();
  private static TimeWeek secondWeek = firstWeek.plusWeeks(1);

  private static TimePeriod period;
  private static TimePeriod samePeriod;
  private static TimePeriod beforePeriod;
  private static TimePeriod afterPeriod;
  private static TimePeriod beforeOverlappingPeriod;
  private static TimePeriod afterOverlappingPeriod;
  private static TimePeriod singleYearPeriod = aTimePeriod().withinYear(firstYear).build();
  private static TimePeriod multipleYearsPeriod =
      aTimePeriod().withinYears(firstYear, secondYear).build();
  private static TimePeriod singleQuarterPeriod = aTimePeriod().withinQuarter(firstQuarter).build();
  private static TimePeriod multipleQuartersPeriod =
      aTimePeriod().withinQuarters(firstQuarter, secondQuarter).build();
  private static TimePeriod singleMonthPeriod = aTimePeriod().withinMonth(firstMonth).build();
  private static TimePeriod multipleMonthsPeriod =
      aTimePeriod().withinMonths(firstMonth, secondMonth).build();
  private static TimePeriod singleWeekPeriod = aTimePeriod().withinWeek(firstWeek).build();
  private static TimePeriod multipleWeeksPeriod =
      aTimePeriod().withinWeeks(firstWeek, secondWeek).build();

  @BeforeEach
  public void setUpPeriod() {
    period = new TimePeriod(start, end);
  }

  @BeforeEach
  public void setUpSamePeriod() {
    samePeriod = new TimePeriod(start, end);
  }

  @BeforeEach
  public void setUpBeforePeriod() {
    TimeDate beforeEnd = start.minusDays(1);
    TimeDate beforeStart = beforeEnd.minusDays(numberOfDays);
    beforePeriod = new TimePeriod(beforeStart, beforeEnd);
  }

  @BeforeEach
  public void setUpAfterPeriod() {
    TimeDate afterStart = end.plusDays(1);
    TimeDate afterEnd = afterStart.plusDays(numberOfDays);
    afterPeriod = new TimePeriod(afterStart, afterEnd);
  }

  @BeforeEach
  public void setUpBeforeOverlappingPeriod() {
    TimeDate beforeOverlappingEnd = start.plusDays(1);
    TimeDate beforeOverlappingStart = beforeOverlappingEnd.minusDays(numberOfDays);
    beforeOverlappingPeriod = new TimePeriod(beforeOverlappingStart, beforeOverlappingEnd);
  }

  @BeforeEach
  public void setUpAfterOverlappingPeriod() {
    TimeDate afterOverlappingStart = end.minusDays(1);
    TimeDate afterOverlappingEnd = afterOverlappingStart.plusDays(numberOfDays);
    afterOverlappingPeriod = new TimePeriod(afterOverlappingStart, afterOverlappingEnd);
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

  // TODO : getYears, getMonths, getQuarters and getWeeks tests are unstable. Builder logic should
  // be rechecked.

  /*
  @Test
  public void getYears_withSingleYearPeriod_shouldGetYear() {
    List<TimeYear> years = singleYearPeriod.getYears();

    assertEquals(1, years.size());
    assertEquals(firstYear, years.get(0));
  }

  @Test
  public void getYears_withMultipleYearsPeriod_shouldGetYears() {
    List<TimeYear> years = multipleYearsPeriod.getYears();

    assertEquals(2, years.size());
    assertEquals(firstYear, years.get(0));
    assertEquals(secondYear, years.get(1));
  }

  @Test
  public void getQuarters_withSingleQuarterPeriod_shouldGetQuarter() {
    List<TimeQuarter> quarters = singleQuarterPeriod.getQuarters();

    assertEquals(1, quarters.size());
    assertEquals(firstQuarter, quarters.get(0));
  }

  @Test
  public void getQuarters_withMultipleQuartersPeriod_shouldGetQuarters() {
    List<TimeQuarter> quarters = multipleQuartersPeriod.getQuarters();

    assertEquals(2, quarters.size());
    assertEquals(firstQuarter, quarters.get(0));
    assertEquals(secondQuarter, quarters.get(1));
  }

  @Test
  public void getMonths_withSingleMonthPeriod_shouldGetMonth() {
    List<TimeMonth> months = singleMonthPeriod.getMonths();

    assertEquals(1, months.size());
    assertEquals(firstMonth, months.get(0));
  }

  @Test
  public void getMonths_withMultipleMonthsPeriod_shouldGetMonths() {
    List<TimeMonth> months = multipleMonthsPeriod.getMonths();

    assertEquals(2, months.size());
    assertEquals(firstMonth, months.get(0));
    assertEquals(secondMonth, months.get(1));
  }

  @Test
  public void getWeeks_withSingleWeekPeriod_shouldGetWeek() {
    List<TimeWeek> weeks = singleWeekPeriod.getWeeks();

    assertEquals(1, weeks.size());
    assertEquals(firstWeek, weeks.get(0));
  }

  @Test
  public void getWeeks_withMultipleWeeksPeriod_shouldGetWeeks() {
    List<TimeWeek> weeks = multipleWeeksPeriod.getWeeks();

    assertEquals(2, weeks.size());
    assertEquals(firstWeek, weeks.get(0));
    assertEquals(secondWeek, weeks.get(1));
  }
  */

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingPeriod() {
    Object object = new Object();

    boolean result = period.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenStartsAreNotEqual() {
    TimePeriod otherPeriod = new TimePeriod(start.plusDays(1), end);

    boolean result = period.equals(otherPeriod);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenEndsAreNotEqual() {
    TimePeriod otherPeriod = new TimePeriod(start, end.plusDays(1));

    boolean result = period.equals(otherPeriod);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    TimePeriod otherPeriod = new TimePeriod(start, end);

    boolean result = period.equals(otherPeriod);

    assertTrue(result);
  }
}
