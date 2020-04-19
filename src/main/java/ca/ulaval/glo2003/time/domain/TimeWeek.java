package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.Calendar;

// TODO : Test TimeWeek
public class TimeWeek implements Comparable<TimeWeek> {

  private TimeYear year;
  private int weekOfYear;

  public TimeWeek(TimeYear year, int weekOfYear) {
    this.year = year;
    this.weekOfYear = weekOfYear;
  }

  public TimeWeek(LocalDate date) {
    this.year = new TimeYear(date);
    this.weekOfYear = date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
  }

  public TimeYear getYear() {
    return year;
  }

  public int toWeekOfYear() {
    return weekOfYear;
  }

  public TimePeriod toPeriod() {
    return new TimePeriod(atFirstDay(), atLastDay());
  }

  public TimeDate atFirstDay() {
    return atDay(Calendar.MONDAY);
  }

  public TimeDate atLastDay() {
    return atDay(Calendar.SUNDAY);
  }

  private TimeDate atDay(int dayOfWeek) {
    Calendar calendar = Calendar.getInstance();
    calendar.setFirstDayOfWeek(Calendar.MONDAY);
    calendar.set(Calendar.YEAR, year.toYear().getValue());
    calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
    calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    return new TimeDate(calendar);
  }

  // TODO : What if there is more that one week added?
  public TimeWeek plusWeeks(int weeks) {
    return year.getNumberOfWeeks() == weekOfYear
        ? new TimeWeek(year.plusYears(1), 1)
        : new TimeWeek(year, weekOfYear + weeks);
  }

  public int firstMonth() {
    Calendar calendar = weekCalendar();
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return calendar.get(Calendar.MONTH) + 1;
  }

  public int lastMonth() {
    Calendar calendar = weekCalendar();
    calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    return calendar.get(Calendar.MONTH) + 1;
  }

  public int firstQuarter() {
    return firstMonth() / 3;
  }

  public int lastQuarter() {
    return lastMonth() / 3;
  }

  private Calendar weekCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year.toYear().getValue());
    calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
    return calendar;
  }

  @Override
  public String toString() {
    return Integer.toString(weekOfYear);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeWeek other = (TimeWeek) object;

    return weekOfYear == other.toWeekOfYear();
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(weekOfYear);
  }

  @Override
  public int compareTo(TimeWeek other) {
    return weekOfYear - other.toWeekOfYear();
  }
}
