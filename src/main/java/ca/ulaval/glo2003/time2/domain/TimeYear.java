package ca.ulaval.glo2003.time2.domain;

import java.time.ZonedDateTime;
import java.util.Calendar;

public class TimeYear extends TimeCalendar {

  TimePeriod period;

  public TimeYear(ZonedDateTime zonedDateTime) {
    super(zonedDateTime);
    calendar.set(Calendar.YEAR, getYear());
    period = new TimePeriod(firstDate(), lastDate());
  }

  public TimePeriod toPeriod() {
    return period;
  }

  private TimeDate firstDate() {
    int firstDayOfYear = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
    return thatDate(firstDayOfYear);
  }

  private TimeDate lastDate() {
    int lastDayOfYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
    return thatDate(lastDayOfYear);
  }

  private TimeDate thatDate(int dayOfYear) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR, getYear());
    date.set(Calendar.DAY_OF_YEAR, dayOfYear);
    setAtMidnight(date);
    return new TimeDate(date.getTime());
  }

  @Override
  public String toString() {
    return Integer.toString(calendar.get(Calendar.YEAR));
  }

  @Override
  public int compareTo(TimeCalendar other) {
    return getYear() - other.getYear();
  }

  // TODO : Test equals and hashCode (can't TimeCalendar do that?)
  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeCalendar other = (TimeCalendar) object;

    return getYear() == other.getYear();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(getYear());
  }
}
