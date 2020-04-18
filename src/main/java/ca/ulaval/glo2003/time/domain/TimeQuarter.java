package ca.ulaval.glo2003.time.domain;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.IsoFields;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

// TODO : Test TimeQuarter
public class TimeQuarter {

  private static List<Integer> POSSIBLE_QUARTERS = Arrays.asList(1, 2, 3, 4);

  public static int firstPossibleQuarter() {
    return POSSIBLE_QUARTERS.get(0);
  }

  public static int lastPossibleQuarter() {
    return POSSIBLE_QUARTERS.get(POSSIBLE_QUARTERS.size() - 1);
  }

  private TimeYear year;
  private int quarterOfYear;

  public TimeQuarter(TimeYear year, int quarterOfYear) {
    this.year = year;
    this.quarterOfYear = quarterOfYear;
  }

  public TimeQuarter(LocalDate date) {
    this.year = new TimeYear(date);
    this.quarterOfYear = date.get(IsoFields.QUARTER_OF_YEAR);
  }

  public TimeYear getYear() {
    return year;
  }

  public int getQuarterOfYear() {
    return quarterOfYear;
  }

  public TimePeriod toPeriod() {
    return new TimePeriod(atFirstDay(), atLastDay());
  }

  public YearMonth atMonth(TimeMonth timeMonth) {
    return year.atMonth(timeMonth);
  }

  public TimeDate atFirstDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year.toYear().getValue());
    calendar.set(Calendar.MONTH, firstMonthOfQuarter());
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    return new TimeDate(calendar);
  }

  public TimeDate atLastDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year.toYear().getValue());
    calendar.set(Calendar.MONTH, lastMonthOfQuarter());
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
    return new TimeDate(calendar);
  }

  public int firstMonthOfQuarter() {
    return ((quarterOfYear - 1) * 3) + 1;
  }

  public int lastMonthOfQuarter() {
    return ((quarterOfYear - 1) * 3) + 3;
  }

  // TODO : What if there is more that one quarter added?
  public TimeQuarter plusQuarters(int quarters) {
    return quarters == lastPossibleQuarter()
        ? new TimeQuarter(year.plusYears(1), firstPossibleQuarter())
        : new TimeQuarter(year, quarterOfYear + quarters);
  }

  @Override
  public String toString() {
    return "q" + quarterOfYear;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimeQuarter other = (TimeQuarter) object;

    return quarterOfYear == other.getQuarterOfYear() && year.equals(other.getYear());
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(quarterOfYear);
  }
}
