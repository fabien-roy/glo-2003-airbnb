package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.util.Calendar;

public class TimeDate {

  private LocalDate date;
  private TimeDay day;

  public static TimeDate now() {
    return new TimeDate(LocalDate.now());
  }

  public TimeDate(LocalDate date) {
    this.date = date;
    this.day = new TimeDay(date);
  }

  public TimeDate(Calendar calendar) {
    this(new Timestamp(calendar.toInstant()).toLocalDate());
  }

  public TimeYear getYear() {
    return day.getYear();
  }

  public TimeQuarter getQuarter() {
    return day.getQuarter();
  }

  public TimeMonth getMonth() {
    return day.getMonth();
  }

  public TimeWeek getWeek() {
    return day.getWeek();
  }

  public int getDayOfYear() {
    return day.getDayOfYear();
  }

  public int getDayOfMonth() {
    return day.getDayOfMonth();
  }

  public TimeDate minusDays(int days) {
    return new TimeDate(date.minusDays(days));
  }

  public TimeDate plusDays(int days) {
    return new TimeDate(date.plusDays(days));
  }

  public boolean isBefore(TimeDate other) {
    return date.isBefore(other.toLocalDate());
  }

  public boolean isAfter(TimeDate other) {
    return date.isAfter(other.toLocalDate());
  }

  public TimePeriod periodToDays(int days) {
    return periodTo(plusDays(days));
  }

  private TimePeriod periodTo(TimeDate other) {
    return new TimePeriod(this, other);
  }

  public TimePeriod toPeriod() {
    return new TimePeriod(this, this);
  }

  public Timestamp toTimestamp() {
    return new Timestamp(date);
  }

  public LocalDate toLocalDate() {
    return date;
  }

  @Override
  public String toString() {
    return date.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeDate timeDate = (TimeDate) object;

    return date.equals(timeDate.toLocalDate());
  }

  @Override
  public int hashCode() {
    return date.hashCode();
  }
}
