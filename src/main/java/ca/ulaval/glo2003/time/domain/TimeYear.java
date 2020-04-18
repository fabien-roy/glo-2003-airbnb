package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

public class TimeYear {

  private Year value;

  public TimeYear(Year value) {
    this.value = value;
  }

  public TimeYear(LocalDate date) {
    this.value = Year.of(date.getYear());
  }

  public Year toYear() {
    return value;
  }

  public TimePeriod toPeriod() {
    return new TimePeriod(atFirstDay(), atLastDay());
  }

  public YearMonth atMonth(TimeMonth month) {
    return value.atMonth(month.toMonth());
  }

  public TimeDate atFirstDay() {
    return new TimeDate(value.atMonth(Month.JANUARY).atDay(1));
  }

  public TimeDate atLastDay() {
    return new TimeDate(value.atMonth(Month.DECEMBER).atEndOfMonth());
  }

  @Override
  public String toString() {
    return ""; // TODO
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeYear other = (TimeYear) object;

    return value.equals(other.toYear());
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
