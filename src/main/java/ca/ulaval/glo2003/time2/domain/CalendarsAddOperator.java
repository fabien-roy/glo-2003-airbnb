package ca.ulaval.glo2003.time2.domain;

import java.util.Set;

public interface CalendarsAddOperator {

  void operation(Set<TimeCalendar> calendars, TimeDate date);
}
