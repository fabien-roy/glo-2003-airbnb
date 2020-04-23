package ca.ulaval.glo2003.time2.domain;

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
}
