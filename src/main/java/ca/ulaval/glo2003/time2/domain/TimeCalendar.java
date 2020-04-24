package ca.ulaval.glo2003.time2.domain;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class TimeCalendar implements Comparable<TimeCalendar> {

  protected Calendar calendar;
  protected TimePeriod period;

  public TimeCalendar(ZonedDateTime zonedDateTime) {
    calendar = GregorianCalendar.from(zonedDateTime);
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    period = new TimePeriod(firstDate(), lastDate());
  }

  protected int getYear() {
    return calendar.get(Calendar.YEAR);
  }

  public int getQuarter() {
    return (getMonth() / 3) + 1;
  }

  protected int getYearQuarter() {
    return getYear() * 4 + getQuarter();
  }

  protected int getMonth() {
    return calendar.get(Calendar.MONTH);
  }

  protected int getYearMonth() {
    return getYear() * 12 + getMonth();
  }

  protected int getWeekOfYear() {
    return calendar.get(Calendar.WEEK_OF_YEAR);
  }

  protected int getYearWeekOfYear() {
    return getYear() * 53 + getWeekOfYear();
  }

  protected abstract TimeDate firstDate();

  protected abstract TimeDate lastDate();

  public TimePeriod toPeriod() {
    return period;
  }

  protected void setAtMidnight(Calendar date) {
    date.set(Calendar.HOUR_OF_DAY, 0);
    date.clear(Calendar.MINUTE);
    date.clear(Calendar.SECOND);
    date.clear(Calendar.MILLISECOND);
  }
}
