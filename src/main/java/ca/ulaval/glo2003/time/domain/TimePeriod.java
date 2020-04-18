package ca.ulaval.glo2003.time.domain;

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

  // TODO : Getting years, months, quarters, ... is pretty similar.
  // TODO : Also, what if quarters, months, ... surpasses superior calendar entity? Does sorting still work?
  public List<TimeYear> getYears() {
    Set<TimeYear> years = new HashSet<>();
    getDates().forEach(date -> years.add(date.getYear()));
    List<TimeYear> uniqueYears = new ArrayList<>(years);
    Collections.sort(uniqueYears);
    return uniqueYears;
  }

  public List<TimeQuarter> getQuarters() {
    Set<TimeQuarter> quarters = new HashSet<>();
    getDates().forEach(date -> quarters.add(date.getQuarter()));
    List<TimeQuarter> uniqueQuarters = new ArrayList<>(quarters);
    Collections.sort(uniqueQuarters);
    return uniqueQuarters;
  }

  public List<TimeMonth> getMonths() {
    Set<TimeMonth> months = new HashSet<>();
    getDates().forEach(date -> months.add(date.getMonth()));
    List<TimeMonth> uniqueMonths = new ArrayList<>(months);
    Collections.sort(uniqueMonths);
    return uniqueMonths;
  }

  public List<TimeWeek> getWeeks() {
    Set<TimeWeek> weeks = new HashSet<>();
    getDates().forEach(date -> weeks.add(date.getWeek()));
    List<TimeWeek> uniqueWeeks = new ArrayList<>(weeks);
    Collections.sort(uniqueWeeks);
    return uniqueWeeks;
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
