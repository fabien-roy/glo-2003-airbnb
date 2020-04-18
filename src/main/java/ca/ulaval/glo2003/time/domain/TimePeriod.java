package ca.ulaval.glo2003.time.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public boolean isOverlapping(TimePeriod other) {
    return !(start.isAfter(other.getEnd()) || end.isBefore(other.getStart()));
  }

  public boolean contains(TimeDate other) {
    return isOverlapping(other.toPeriod());
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

  public List<TimeYear> getYears() {
    Set<TimeYear> years = new HashSet<>();
    getDates().forEach(date -> years.add(date.getYear()));
    return new ArrayList<>(years);
  }

  public List<TimeMonth> getMonths() {
    Set<TimeMonth> months = new HashSet<>();
    getDates().forEach(date -> months.add(date.getMonth()));
    return new ArrayList<>(months);
  }

  public List<TimeWeek> getWeeks() {
    Set<TimeWeek> weeks = new HashSet<>();
    getDates().forEach(date -> weeks.add(date.getWeek()));
    return new ArrayList<>(weeks);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;

    TimePeriod timePeriod = (TimePeriod) object;

    return start.equals(timePeriod.getStart()) && end.equals(timePeriod.getEnd());
  }

  @Override
  public int hashCode() {
    return start.hashCode() + end.hashCode();
  }
}
