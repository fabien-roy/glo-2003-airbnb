package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.Month;

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
    return month.getValue() == quarter.lastMonthOfQuarter() + 1
        ? new TimeMonth(quarter.plusQuarters(1), month.plus(months))
        : new TimeMonth(quarter, month.plus(months));
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
