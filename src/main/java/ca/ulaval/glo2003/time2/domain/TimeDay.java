package ca.ulaval.glo2003.time2.domain;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeDay {

  Calendar day;

  public TimeDay(ZonedDateTime zonedDateTime) {
    day = GregorianCalendar.from(zonedDateTime);
  }

  public Calendar toDay() {
    return day;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeDay other = (TimeDay) object;

    return day.equals(other.toDay());
  }

  @Override
  public int hashCode() {
    return day.hashCode();
  }
}
