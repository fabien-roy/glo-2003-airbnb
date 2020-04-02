package ca.ulaval.glo2003.bookings.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingPeriodTest {

  private static int numberOfDays = 5;
  private static BookingDate start = BookingDate.now();
  private static BookingDate end = start.plusDays(numberOfDays);

  private static BookingPeriod period;
  private static BookingPeriod samePeriod;
  private static BookingPeriod beforePeriod;
  private static BookingPeriod afterPeriod;
  private static BookingPeriod beforeOverlappingPeriod;
  private static BookingPeriod afterOverlappingPeriod;

  @BeforeAll
  public static void setUpPeriod() {
    period = new BookingPeriod(start, end);
  }

  @BeforeEach
  public void setUpSamePeriod() {
    samePeriod = new BookingPeriod(start, end);
  }

  @BeforeEach
  public void setUpBeforePeriod() {
    BookingDate beforeEnd = start.minusDays(1);
    BookingDate beforeStart = beforeEnd.minusDays(numberOfDays);
    beforePeriod = new BookingPeriod(beforeStart, beforeEnd);
  }

  @BeforeEach
  public void setUpAfterPeriod() {
    BookingDate afterStart = end.plusDays(1);
    BookingDate afterEnd = afterStart.plusDays(numberOfDays);
    afterPeriod = new BookingPeriod(afterStart, afterEnd);
  }

  @BeforeEach
  public void setUpBeforeOverlappingPeriod() {
    BookingDate beforeOverlappingEnd = start.plusDays(1);
    BookingDate beforeOverlappingStart = beforeOverlappingEnd.minusDays(numberOfDays);
    beforeOverlappingPeriod = new BookingPeriod(beforeOverlappingStart, beforeOverlappingEnd);
  }

  @BeforeEach
  public void setUpAfterOverlappingPeriod() {
    BookingDate afterOverlappingStart = end.minusDays(1);
    BookingDate afterOverlappingEnd = afterOverlappingStart.plusDays(numberOfDays);
    afterOverlappingPeriod = new BookingPeriod(afterOverlappingStart, afterOverlappingEnd);
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
  public void getDates_shouldGetDates() {
    List<BookingDate> dates = period.getDates();

    for (int i = 0; i < numberOfDays; i++) {
      assertEquals(start.plusDays(i), dates.get(i));
    }
  }

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingPeriod() {
    Object object = new Object();

    boolean result = period.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenStartsAreNotEqual() {
    BookingPeriod otherPeriod = new BookingPeriod(start.plusDays(1), end);

    boolean result = period.equals(otherPeriod);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenEndsAreNotEqual() {
    BookingPeriod otherPeriod = new BookingPeriod(start, end.plusDays(1));

    boolean result = period.equals(otherPeriod);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    BookingPeriod otherPeriod = new BookingPeriod(start, end);

    boolean result = period.equals(otherPeriod);

    assertTrue(result);
  }
}