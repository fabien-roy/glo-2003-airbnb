package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class TimeWeek {

  WeekFields weekFields =
      WeekFields.of(Locale.CANADA_FRENCH); // TODO : With a converter, this wouldn't be a problem.

  private int weekOfYear;

  public TimeWeek(LocalDate date) {
    this.weekOfYear = date.get(weekFields.weekOfWeekBasedYear());
  }

  public int toWeekOfYear() {
    return weekOfYear;
  }

  @Override
  public String toString() {
    return ""; // TODO
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
