package ca.ulaval.glo2003.time2.domain;

import java.time.LocalDate;
import java.util.Date;

public class TimeDate {

  private LocalDate date;

  public static TimeDate now() {
    return new TimeDate(LocalDate.now());
  }

  public TimeDate(LocalDate date) {
    this.date = date;
  }

  public TimeDate(Date date) {
    this.date = new Timestamp(date.toInstant()).toLocalDate();
  }

  // TODO : Test TimeDate.getYear
  public TimeYear getYear() {
    return new TimeYear(toTimestamp().toZonedDateTime());
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

  public TimePeriod toPeriod() {
    return new TimePeriod(this, this);
  }

  public TimePeriod toPeriod(int days) {
    return new TimePeriod(this, plusDays(days));
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
