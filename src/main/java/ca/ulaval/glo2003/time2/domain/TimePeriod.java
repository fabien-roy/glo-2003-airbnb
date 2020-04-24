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

  // TODO : Test TimePeriod.getYears
  public List<TimeCalendar> getYears() {
    return getCalendars((calendars, date) -> calendars.add(date.getYear()));
  }

  private List<TimeCalendar> getCalendars(CalendarsAddOperator addOperator) {
    Set<TimeCalendar> calendars = new HashSet<>();
    getDates().forEach(date -> addOperator.operation(calendars, date));
    List<TimeCalendar> uniqueCalendars = new ArrayList<>(calendars);
    Collections.sort(uniqueCalendars);
    return uniqueCalendars;
  }

  public boolean isOverlapping(TimePeriod other) {
    return !(start.isAfter(other.getEnd()) || end.isBefore(other.getStart()));
  }

  public boolean contains(TimeDate other) {
    return isOverlapping(other.toPeriod());
  }
}
