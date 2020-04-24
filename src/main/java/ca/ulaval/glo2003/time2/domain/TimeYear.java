package ca.ulaval.glo2003.time2.domain;

import java.util.Calendar;

public class TimeYear {

  Calendar year;
  TimePeriod period;

  public TimeYear(Calendar calendar) {
    year = Calendar.getInstance();
    year.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
    period = new TimePeriod(firstDate(), lastDate());
  }

  public TimePeriod getPeriod() {
    return period;
  }

  private TimeDate firstDate() {
    int firstDayOfYear = year.getActualMinimum(Calendar.DAY_OF_YEAR);
    return thatDate(firstDayOfYear);
  }

  private TimeDate lastDate() {
    int firstDayOfYear = year.getActualMaximum(Calendar.DAY_OF_YEAR);
    return thatDate(firstDayOfYear);
  }

  private TimeDate thatDate(int dayOfTheYear) {
    Calendar date = Calendar.getInstance();
    date.set(Calendar.YEAR, year.get(Calendar.YEAR));
    date.set(Calendar.DAY_OF_YEAR, dayOfTheYear);
    date.set(Calendar.HOUR_OF_DAY, 0);
    date.set(Calendar.MINUTE, 0);
    date.set(Calendar.SECOND, 0);
    date.set(Calendar.MILLISECOND, 0);
    return new TimeDate(date.getTime());
  }

  @Override
  public String toString() {
    return Integer.toString(year.get(Calendar.YEAR));
  }
}
