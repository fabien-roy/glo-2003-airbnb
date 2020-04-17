package ca.ulaval.glo2003.interfaces.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReservationPeriodTest {

  private static int numberOfDays = 5;
  private static int firstYear = 2020;
  private static int secondYear = 2021;
  private static ReservationDate start = ReservationDate.now();
  private static ReservationDate end = start.plusDays(numberOfDays);

  private static ReservationPeriod period;
  private static ReservationPeriod samePeriod;
  private static ReservationPeriod beforePeriod;
  private static ReservationPeriod afterPeriod;
  private static ReservationPeriod beforeOverlappingPeriod;
  private static ReservationPeriod afterOverlappingPeriod;
  private static ReservationPeriod singleYearPeriod;
  private static ReservationPeriod multipleYearsPeriod;

  @BeforeEach
  public void setUpPeriod() {
    period = new ReservationPeriod(start, end);
  }

  @BeforeEach
  public void setUpSamePeriod() {
    samePeriod = new ReservationPeriod(start, end);
  }

  @BeforeEach
  public void setUpBeforePeriod() {
    ReservationDate beforeEnd = start.minusDays(1);
    ReservationDate beforeStart = beforeEnd.minusDays(numberOfDays);
    beforePeriod = new ReservationPeriod(beforeStart, beforeEnd);
  }

  @BeforeEach
  public void setUpAfterPeriod() {
    ReservationDate afterStart = end.plusDays(1);
    ReservationDate afterEnd = afterStart.plusDays(numberOfDays);
    afterPeriod = new ReservationPeriod(afterStart, afterEnd);
  }

  @BeforeEach
  public void setUpBeforeOverlappingPeriod() {
    ReservationDate beforeOverlappingEnd = start.plusDays(1);
    ReservationDate beforeOverlappingStart = beforeOverlappingEnd.minusDays(numberOfDays);
    beforeOverlappingPeriod = new ReservationPeriod(beforeOverlappingStart, beforeOverlappingEnd);
  }

  @BeforeEach
  public void setUpAfterOverlappingPeriod() {
    ReservationDate afterOverlappingStart = end.minusDays(1);
    ReservationDate afterOverlappingEnd = afterOverlappingStart.plusDays(numberOfDays);
    afterOverlappingPeriod = new ReservationPeriod(afterOverlappingStart, afterOverlappingEnd);
  }

  @BeforeEach
  public void setUpSingleYearPeriod() {
    singleYearPeriod = ReservationPeriod.ofYear(firstYear);
  }

  @BeforeEach
  public void setUpMultipleYearsPeriod() {
    multipleYearsPeriod =
        new ReservationPeriod(
            ReservationDate.firstDayOfYear(firstYear), ReservationDate.lastDayOfYear(secondYear));
  }

  @ParameterizedTest
  @ValueSource(ints = {2020, 2035, 1976})
  public void ofYear_shouldHaveFirstDayOfYearAsStart(int year) {
    ReservationDate firstDayOfYear = ReservationDate.firstDayOfYear(year);

    ReservationPeriod period = ReservationPeriod.ofYear(year);

    assertEquals(firstDayOfYear, period.getStart());
  }

  @ParameterizedTest
  @ValueSource(ints = {2020, 2035, 1976})
  public void ofYear_shouldHaveLastDayOfYearAsEnd(int year) {
    ReservationDate lastDayOfYear = ReservationDate.lastDayOfYear(year);

    ReservationPeriod period = ReservationPeriod.ofYear(year);

    assertEquals(lastDayOfYear, period.getEnd());
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
    period = new ReservationPeriod(start, start);

    List<ReservationDate> dates = period.getDates();

    assertEquals(start, dates.get(0));
  }

  @Test
  public void getDates_shouldGetDates() {
    List<ReservationDate> dates = period.getDates();

    for (int i = 0; i < numberOfDays; i++) {
      assertEquals(start.plusDays(i), dates.get(i));
    }
  }

  @Test
  public void getYears_withOneYear_shouldSingleYear() {
    List<Integer> years = singleYearPeriod.getYears();

    assertEquals(1, years.size());
    assertEquals(firstYear, years.get(0));
  }

  @Test
  public void getYears_withMultipleYears_shouldMultipleYears() {
    List<Integer> years = multipleYearsPeriod.getYears();

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
    ReservationPeriod otherPeriod = new ReservationPeriod(start.plusDays(1), end);

    boolean result = period.equals(otherPeriod);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenEndsAreNotEqual() {
    ReservationPeriod otherPeriod = new ReservationPeriod(start, end.plusDays(1));

    boolean result = period.equals(otherPeriod);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    ReservationPeriod otherPeriod = new ReservationPeriod(start, end);

    boolean result = period.equals(otherPeriod);

    assertTrue(result);
  }
}
