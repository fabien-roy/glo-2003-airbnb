package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

// TODO : Test TimeWeek
public class TimeWeek {

  WeekFields weekFields =
      WeekFields.of(Locale.CANADA_FRENCH); // TODO : With a converter, this wouldn't be a problem.

  private TimeYear year;
  private int weekOfYear;

  public TimeWeek(TimeYear year, int weekOfYear) {
    this.year = year;
    this.weekOfYear = weekOfYear;
  }

  public TimeWeek(LocalDate date) {
    this.year = new TimeYear(date);
    this.weekOfYear = date.get(weekFields.weekOfWeekBasedYear());
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
    calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
    calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    return new TimeDate(calendar);
  }

  // TODO : What if there is more that one week added?
  public TimeWeek plusWeeks(int weeks) {
    return year.getWeeks() == weekOfYear
        ? new TimeWeek(year.plusYears(1), 0)
        : new TimeWeek(year, weekOfYear + weeks);
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
}
