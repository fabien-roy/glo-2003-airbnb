package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;

public class TimeDay {

  private TimeMonth month;
  private TimeWeek week;
  private int dayOfYear;
  private int dayOfMonth;
  private int dayOfWeek;

  public TimeDay(TimeMonth month, TimeWeek week, int dayOfYear, int dayOfMonth, int dayOfWeek) {
    this.month = month;
    this.week = week;
    this.dayOfYear = dayOfYear;
    this.dayOfMonth = dayOfMonth;
    this.dayOfWeek = dayOfWeek;
  }

  public TimeDay(LocalDate date) {
    month = new TimeMonth(date);
    week = new TimeWeek(date);
    dayOfYear = date.getDayOfYear();
    dayOfMonth = date.getDayOfMonth();
    dayOfWeek = date.getDayOfWeek().getValue();
  }

  public TimeYear getYear() {
    return month.getYear();
  }

  public TimeMonth getMonth() {
    return month;
  }

  public TimeWeek getWeek() {
    return week;
  }

  public int getDayOfYear() {
    return dayOfYear;
  }

  public int getDayOfMonth() {
    return dayOfMonth;
  }

  public TimeDate toDate() {
    return new TimeDate(LocalDate.of(month.getYear().toYear().getValue(), month.toMonth().getValue(), dayOfMonth));
  }

  @Override
  public String toString() {
    return ""; // TODO
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeDay other = (TimeDay) object;

    return dayOfYear == other.dayOfYear
        && dayOfMonth == other.dayOfMonth
        && dayOfWeek == other.dayOfWeek
        && month.equals(other.getMonth())
        && week.equals(other.getWeek());
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(dayOfYear + dayOfMonth + dayOfWeek);
  }
}
