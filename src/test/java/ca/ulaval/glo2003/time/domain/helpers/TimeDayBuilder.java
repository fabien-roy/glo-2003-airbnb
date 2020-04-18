package ca.ulaval.glo2003.time.domain.helpers;

import ca.ulaval.glo2003.time.domain.*;

import static ca.ulaval.glo2003.time.domain.helpers.TimeDateObjectMother.*;
import static ca.ulaval.glo2003.time.domain.helpers.TimeMonthBuilder.aTimeMonth;
import static ca.ulaval.glo2003.time.domain.helpers.TimeWeekBuilder.aTimeWeek;

public class TimeDayBuilder {

  private TimeMonth month = null;
  private TimeWeek week = null;
  private int dayOfYear = -1;
  private int dayOfMonth = -1;
  private int dayOfWeek = -1;

  public static TimeDayBuilder aTimeDay() {
    return new TimeDayBuilder();
  }

  public TimeDayBuilder withYear(TimeYear year) {
    month = aTimeMonth().withYear(year).build();
    return this;
  }

  public TimeDayBuilder withQuarter(TimeQuarter quarter) {
    month = aTimeMonth().withQuarter(quarter).build();
    return this;
  }

  public TimeDayBuilder withMonth(TimeMonth month) {
    this.month = month;
    return this;
  }

  public TimeDayBuilder withWeek(TimeWeek week) {
    this.week = week;
    return this;
  }

  public TimeDay build() {
    if (month != null) week = aTimeWeek().withMonth(month).build();
    else if (week != null) month = aTimeMonth().withWeek(week).build();

    if (week == null) week = aTimeWeek().build();
    if (month == null) month = aTimeMonth().withWeek(week).build();

    if (dayOfMonth == -1) dayOfMonth = createDayOfMonth(month, week);
    if (dayOfYear == -1) dayOfYear = createDayOfYear(month, dayOfMonth);
    if (dayOfWeek == -1) dayOfWeek = createDayOfWeek(week, dayOfYear);

    return new TimeDay(month, week, dayOfYear, dayOfMonth, dayOfWeek);
  }
}
