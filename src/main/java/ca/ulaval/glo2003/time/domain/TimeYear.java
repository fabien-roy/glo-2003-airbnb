package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

public class TimeYear {

  private Year year;

  public TimeYear(Year year) {
    this.year = year;
  }

  public TimeYear(LocalDate date) {
    this.year = Year.of(date.getYear());
  }

  public Year toYear() {
    return year;
  }

  public TimePeriod toPeriod() {
    return new TimePeriod(atFirstDay(), atLastDay());
  }

  public YearMonth atMonth(TimeMonth month) {
    return year.atMonth(month.toMonth());
  }

  public TimeDate atFirstDay() {
    return new TimeDate(year.atMonth(Month.JANUARY).atDay(1));
  }

  public TimeDate atLastDay() {
    return new TimeDate(year.atMonth(Month.DECEMBER).atEndOfMonth());
  }

  public TimeYear plusYears(int years) {
    Year newYear = this.year.plusYears(years);
    return new TimeYear(newYear);
  }

  @Override
  public String toString() {
    return ""; // TODO
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeYear other = (TimeYear) object;

    return year.equals(other.toYear());
  }

  @Override
  public int hashCode() {
    return year.hashCode();
  }
}
