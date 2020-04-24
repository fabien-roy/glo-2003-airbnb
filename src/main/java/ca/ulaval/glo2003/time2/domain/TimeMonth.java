package ca.ulaval.glo2003.time2.domain;

import ca.ulaval.glo2003.admin.domain.Configuration;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class TimeMonth extends TimeCalendar {

  TimePeriod period;

  public TimeMonth(ZonedDateTime zonedDateTime) {
    super(zonedDateTime);
    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
    period = new TimePeriod(firstDate(), lastDate());
  }

  public TimePeriod toPeriod() {
    return period;
  }

  private TimeDate firstDate() {
    int firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    return thatDate(firstDayOfMonth);
  }

  private TimeDate lastDate() {
    int lastDayOfMonth = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
    return thatDate(lastDayOfMonth);
  }

  private TimeDate thatDate(int dayOfMonth) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR, getYear());
    date.set(Calendar.MONTH, getMonth());
    date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    setAtMidnight(date);
    return new TimeDate(date.getTime());
  }

  @Override
  public String toString() {
    return calendar
        .getDisplayName(Calendar.MONTH, Calendar.LONG, Configuration.instance().getLocale())
        .toLowerCase();
  }

  @Override
  public int compareTo(TimeCalendar other) {
    return getYearMonth() - other.getYearMonth();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeCalendar other = (TimeCalendar) object;

    return getYearMonth() == other.getYearMonth();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(getYearMonth());
  }
}
