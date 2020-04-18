package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.Month;

// TODO : Test TimeMonth
public class TimeMonth {

  private TimeYear year;
  private Month value;

  public TimeMonth(TimeYear year, Month value) {
    this.year = year;
    this.value = value;
  }

  public TimeMonth(LocalDate date) {
    this.year = new TimeYear(date);
    this.value = date.getMonth();
  }

  public TimeYear getYear() {
    return year;
  }

  public Month toMonth() {
    return value;
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

  @Override
  public String toString() {
    return ""; // TODO
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeMonth other = (TimeMonth) object;

    return value.equals(other.toMonth()) && year.equals(other.getYear());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
