package ca.ulaval.glo2003.time2.domain.helpers;

import java.util.Calendar;

public class CalendarBuilder {

  public static int UNSER_INT = -1;

  public int year = UNSER_INT;

  public static CalendarBuilder aCalendar() {
    return new CalendarBuilder();
  }

  public CalendarBuilder withYear(int year) {
    this.year = year;
    return this;
  }

  public Calendar build() {
    Calendar calendar = Calendar.getInstance();

    if (year != UNSER_INT) calendar.set(Calendar.YEAR, year);

    return calendar;
  }
}
