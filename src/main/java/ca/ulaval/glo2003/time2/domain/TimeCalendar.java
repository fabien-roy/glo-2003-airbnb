package ca.ulaval.glo2003.time2.domain;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class TimeCalendar implements Comparable<TimeCalendar> {

  protected Calendar calendar;

  public TimeCalendar(ZonedDateTime zonedDateTime) {
    calendar = GregorianCalendar.from(zonedDateTime);
  }

  protected int getYear() {
    return calendar.get(Calendar.YEAR);
  }

  public abstract TimePeriod toPeriod();

  public Calendar toCalendar() {
    return calendar;
  }

  protected void setAtMidnight(Calendar date) {
    date.set(Calendar.HOUR_OF_DAY, 0);
    date.set(Calendar.MINUTE, 0);
    date.set(Calendar.SECOND, 0);
    date.set(Calendar.MILLISECOND, 0);
  }

  // TODO : Move TimeDay.equals tests here
  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeCalendar other = (TimeCalendar) object;

    return calendar.equals(other.toCalendar());
  }

  @Override
  public int hashCode() {
    return calendar.hashCode();
  }
}
