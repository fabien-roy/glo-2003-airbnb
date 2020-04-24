package ca.ulaval.glo2003.time.domain;

import ca.ulaval.glo2003.admin.domain.Configuration;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
import java.util.Calendar;

public class TimeWeek extends TimeCalendar {

  public TimeWeek(ZonedDateTime zonedDateTime) {
    super(zonedDateTime);
    int weekOfYear =
        zonedDateTime.get(
            WeekFields.of(Configuration.instance().getLocale()).weekOfWeekBasedYear());
    calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear);
  }

  @Override
  protected TimeDate firstDate() {
    Calendar firstDate = weekDate();
    firstDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    return new TimeDate(firstDate.getTime());
  }

  @Override
  protected TimeDate lastDate() {
    Calendar lastDate = weekDate();
    lastDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    return new TimeDate(lastDate.getTime());
  }

  private Calendar weekDate() {
    Calendar date = Calendar.getInstance();
    date.setFirstDayOfWeek(Calendar.MONDAY);
    date.set(Calendar.YEAR, getYear());
    date.set(Calendar.WEEK_OF_YEAR, getWeekOfYear());
    setAtMidnight(date);
    return date;
  }

  @Override
  public String toString() {
    return "week#".concat(Integer.toString(getWeekOfYear()));
  }

  @Override
  public int compareTo(TimeCalendar other) {
    return getYearWeekOfYear() - other.getYearWeekOfYear();
  }
}
