package ca.ulaval.glo2003.time2.domain;

import java.util.*;

public class TimePeriod {

  private TimeDate start;
  private TimeDate end;

  public TimePeriod(TimeDate start, TimeDate end) {
    this.start = start;
    this.end = end;
  }

  public TimeDate getStart() {
    return start;
  }

  public TimeDate getEnd() {
    return end;
  }

  public List<TimeDate> getDates() {
    List<TimeDate> dates = new ArrayList<>();

    TimeDate date = start;
    do {
      dates.add(date);
      date = date.plusDays(1);
    } while (date.isBefore(end));
    dates.add(end);

    return dates;
  }

  public List<TimeCalendar> getYears() {
    return getCalendars(TimeDate::getYear);
  }

  public List<TimeCalendar> getQuarters() {
    return getCalendars(TimeDate::getQuarter);
  }

  public List<TimeCalendar> getMonths() {
    return getCalendars(TimeDate::getMonth);
  }

  public List<TimeCalendar> getWeeks() {
    return getCalendars(TimeDate::getWeek);
  }

  private List<TimeCalendar> getCalendars(GetCalendarOperator getCalendar) {
    List<TimeCalendar> calendars = new ArrayList<>();
    getDates()
        .forEach(
            date -> {
              if (!calendars.contains(getCalendar.operation(date)))
                calendars.add(getCalendar.operation(date));
            });
    Collections.sort(calendars);
    return calendars;
  }

  public boolean isOverlapping(TimePeriod other) {
    return !(start.isAfter(other.getEnd()) || end.isBefore(other.getStart()));
  }

  public boolean contains(TimeDate other) {
    return isOverlapping(other.toPeriod());
  }
}
