package ca.ulaval.glo2003.time.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateBuilder.aTimeDate;
import static ca.ulaval.glo2003.time.domain.helpers.TimeYearBuilder.aTimeYear;
import static org.junit.jupiter.api.Assertions.*;

class TimePeriodTest {

  private static int numberOfDays = 5;
  private static TimeDate start = TimeDate.now();
  private static TimeDate end = start.plusDays(numberOfDays);
  private static TimeYear firstYear = aTimeYear().build();
  private static TimeYear secondYear = aTimeYear().withYear(firstYear.toYear().plusYears(1)).build();

  private static TimePeriod period;
  private static TimePeriod samePeriod;
  private static TimePeriod beforePeriod;
  private static TimePeriod afterPeriod;
  private static TimePeriod beforeOverlappingPeriod;
  private static TimePeriod afterOverlappingPeriod;
  private static TimePeriod singleYearPeriod;
  private static TimePeriod multipleYearsPeriod;

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

  @BeforeEach
  public void setUpSingleYearPeriod() {
    TimeDate firstDate = aTimeDate().withYear(firstYear).build();
    TimeDate secondDate = aTimeDate().withYear(firstYear).build();
    singleYearPeriod = new TimePeriod(firstDate, secondDate);
  }

  @BeforeEach
  public void setUpMultipleYearsPeriod() {
    TimeDate firstDate = aTimeDate().withYear(firstYear).build();
    TimeDate secondDate = aTimeDate().withYear(secondYear).build();
    multipleYearsPeriod = new TimePeriod(firstDate, secondDate);
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
