package ca.ulaval.glo2003.time2.domain;

import java.time.ZonedDateTime;
import java.util.Calendar;

public class TimeQuarter extends TimeCalendar {

  private int firstMonthOfQuarter;
  private int lastMonthOfQuarter;

  public TimeQuarter(ZonedDateTime zonedDateTime) {
    super(zonedDateTime);
    firstMonthOfQuarter = (getMonth() / 3) * 3;
    lastMonthOfQuarter = firstMonthOfQuarter + 2;
    period = new TimePeriod(firstDate(), lastDate());
  }

  @Override
  protected TimeDate firstDate() {
    Calendar firstMonth = Calendar.getInstance();
    firstMonth.set(Calendar.MONTH, firstMonthOfQuarter);
    int firstDayOfMonth = firstMonth.getActualMinimum(Calendar.DAY_OF_MONTH);
    return thatDate(firstMonthOfQuarter, firstDayOfMonth);
  }

  @Override
  protected TimeDate lastDate() {
    Calendar lastMonth = Calendar.getInstance();
    lastMonth.set(Calendar.MONTH, lastMonthOfQuarter);
    int lastDayOfMonth = lastMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
    return thatDate(lastMonthOfQuarter, lastDayOfMonth);
  }

  private TimeDate thatDate(int month, int dayOfMonth) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR, getYear());
    date.set(Calendar.MONTH, month);
    date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    setAtMidnight(date);
    return new TimeDate(date.getTime());
  }

  @Override
  public String toString() {
    return "q".concat(Integer.toString(getQuarter()));
  }

  @Override
  public int compareTo(TimeCalendar other) {
    return getYearQuarter() - other.getYearQuarter();
  }

  // TODO : Test equals and hashCode (can't TimeCalendar do that?)
  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeCalendar other = (TimeCalendar) object;

    return getYearQuarter() == other.getYearQuarter();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(getYearQuarter());
  }
}
