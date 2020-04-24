package ca.ulaval.glo2003.time2.domain;

import static ca.ulaval.glo2003.time2.domain.helpers.TimeDayBuilder.aDay;
import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class TimeDayTest {

  private static TimeDay day = aDay().build();
  private static TimeDay otherDay = aDay().build();

  @Test
  public void equals_shouldReturnFalse_whenObjectIsNotBookingDate() {
    Object object = new Object();

    boolean result = day.equals(object);

    assertFalse(result);
  }

  @Test
  public void equals_shouldReturnFalse_whenValuesAreNotEqual() {
    boolean result = day.equals(otherDay);

    assertFalse(result);
  }

  // TODO : Can't we produce a same TimeDay in a better way?
  @Test
  public void equals_shouldReturnTrue_whenValuesAreEqual() {
    TimeDay otherDay = new TimeDay(day.toCalendar().toInstant().atZone(ZoneOffset.UTC));

    boolean result = day.equals(otherDay);

    assertTrue(result);
  }
}
