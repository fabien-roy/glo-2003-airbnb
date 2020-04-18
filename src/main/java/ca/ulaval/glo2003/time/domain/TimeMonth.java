package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.Month;

// TODO : Test TimeMonth
public class TimeMonth {

  private TimeYear year;
  private Month month;

  public TimeMonth(TimeYear year, Month month) {
    this.year = year;
    this.month = month;
  }

  public TimeMonth(LocalDate date) {
    this.year = new TimeYear(date);
    this.month = date.getMonth();
  }

  public TimeYear getYear() {
    return year;
  }

  public Month toMonth() {
    return month;
  }

  public TimePeriod toPeriod() {
    return new TimePeriod(atFirstDay(), atLastDay());
  }

  public TimeDate atFirstDay() {
    return new TimeDate(year.atMonth(this).atDay(1));
  }

  public TimeDate atLastDay() {
    return new TimeDate(year.atMonth(this).atEndOfMonth());
  }

  public TimeMonth plusMonths(int months) {
    TimeYear newYear = month.equals(Month.DECEMBER) ? year.plusYears(1) : year;
    Month newValue = this.month.plus(months);
    return new TimeMonth(newYear, newValue);
  }

  @Override
  public String toString() {
    return ""; // TODO
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeMonth other = (TimeMonth) object;

    return month.equals(other.toMonth()) && year.equals(other.getYear());
  }

  @Override
  public int hashCode() {
    return month.hashCode();
  }
}
