package ca.ulaval.glo2003.time2.domain;

import java.util.ArrayList;
import java.util.List;

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

  public boolean isOverlapping(TimePeriod other) {
    return !(start.isAfter(other.getEnd()) || end.isBefore(other.getStart()));
  }

  public boolean contains(TimeDate other) {
    return isOverlapping(other.toPeriod());
  }
}
