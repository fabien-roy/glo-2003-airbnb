package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

// TODO : Test TimeMonth
public class TimeMonth implements Comparable<TimeMonth> {

  private TimeQuarter quarter;
  private Month month;

  public TimeMonth(TimeQuarter quarter, Month month) {
    this.quarter = quarter;
    this.month = month;
  }

  public TimeMonth(LocalDate date) {
    this.quarter = new TimeQuarter(date);
    this.month = date.getMonth();
  }

  public TimeYear getYear() {
    return quarter.getYear();
  }

  public TimeQuarter getQuarter() {
    return quarter;
  }

  public Month toMonth() {
    return month;
  }

  public TimePeriod toPeriod() {
    return new TimePeriod(atFirstDay(), atLastDay());
  }

  public TimeDate atFirstDay() {
    return new TimeDate(quarter.atMonth(this).atDay(1));
  }

  public TimeDate atLastDay() {
    return new TimeDate(quarter.atMonth(this).atEndOfMonth());
  }

  // TODO : What if there is more that one month added?
  public TimeMonth plusMonths(int months) {
    return month.getValue() == quarter.lastMonth() + 1
        ? new TimeMonth(quarter.plusQuarters(1), month.plus(months))
        : new TimeMonth(quarter, month.plus(months));
  }

  public int firstWeek() {
    Calendar calendar = weekCalendar();
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return calendar.get(Calendar.WEEK_OF_YEAR);
  }

  public int lastWeek() {
    Calendar calendar = weekCalendar();
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    return calendar.get(Calendar.WEEK_OF_YEAR);
  }

  private Calendar weekCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, quarter.getYear().toYear().getValue());
    calendar.set(Calendar.MONTH, month.getValue() - 1);
    return calendar;
  }

  @Override
  public String toString() {
    return month.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeMonth other = (TimeMonth) object;

    return month.equals(other.toMonth()) && quarter.equals(other.getQuarter());
  }

  @Override
  public int hashCode() {
    return month.hashCode();
  }

  @Override
  public int compareTo(TimeMonth other) {
    return month.getValue() - other.toMonth().getValue();
  }
}
